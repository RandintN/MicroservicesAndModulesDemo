package simple.software.learn;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {

  public void registerCustomer(CustomerRegistrationRequest request) {
    Customer customer = Customer.builder()
        .firstName(request.firstName())
        .lastName(request.lastname())
        .email(request.email())
        .build();

    // TODO: check if email is valid
    customerRepository.saveAndFlush(customer);
    // TODO: check if email not taken
    final var fraudCheckResponse = restTemplate.getForObject(
        "http://FRAUD/api/v1/fraud-check/{customerId}", FraudCheckResponse.class,
        customer.getId());

    if (fraudCheckResponse != null && fraudCheckResponse.isFraudster()) {
      throw new IllegalStateException("Fraudster");
    }
    // TODO: send notification

  }
}
