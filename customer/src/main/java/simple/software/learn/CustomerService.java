package simple.software.learn;

import org.springframework.stereotype.Service;
import simple.software.learn.clients.fraud.FraudCheckResponse;
import simple.software.learn.clients.fraud.FraudClient;

@Service
public record CustomerService(CustomerRepository customerRepository, FraudClient fraudClient) {

  public void registerCustomer(CustomerRegistrationRequest request) {
    Customer customer = Customer.builder()
        .firstName(request.firstName())
        .lastName(request.lastname())
        .email(request.email())
        .build();

    // TODO: check if email is valid
    customerRepository.saveAndFlush(customer);
    // TODO: check if email not taken
    final FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

    if (fraudCheckResponse != null && fraudCheckResponse.isFraudster()) {
      throw new IllegalStateException("Fraudster");
    }

    // TODO: send notification

  }
}
