package simple.software.learn.customer;

import org.springframework.stereotype.Service;
import simple.software.learn.ampq.RabbitMqMessageProducer;
import simple.software.learn.clients.fraud.FraudCheckResponse;
import simple.software.learn.clients.fraud.FraudClient;
import simple.software.learn.clients.notification.NotificationRequest;

@Service
public record CustomerService(CustomerRepository customerRepository,
                              FraudClient fraudClient,
                              RabbitMqMessageProducer messageProducer) {

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

    final var notificationRequest = new NotificationRequest(
        customer.getId(),
        customer.getEmail(),
        String.format("Hi %s, welcome to my kingdom", customer.getFirstName())
    );

    messageProducer.publish(notificationRequest,
                    "internal.exchange",
                   "internal.notification.routing-key");
  }
}
