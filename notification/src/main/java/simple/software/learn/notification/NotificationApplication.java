package simple.software.learn.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
    "simple.software.learn.ampq",
    "simple.software.learn.notification"
})
public class NotificationApplication {

  public static void main(String[] args) {
    SpringApplication.run(NotificationApplication.class, args);
  }

 /* @Bean
  CommandLineRunner runner(RabbitMqMessageProducer producer,
                           NotificationConfig notificationConfig) {
    return args -> producer.publish(new Person("Robson", 32),
        notificationConfig.getInternalExchange(),
        notificationConfig.getInternalNotificationRoutingKey());
  }

  record Person(String name, int age) {}*/

}