package simple.software.learn.notification.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import simple.software.learn.clients.notification.NotificationRequest;
import simple.software.learn.notification.NotificationService;

@Slf4j
@Component
public record NotificationConsumer(NotificationService notificationService) {

  @RabbitListener(queues = "${rabbitmq.template.queue.notification}")
  public void consumer(NotificationRequest notificationRequest) {
    log.info("Consumed {} from queue", notificationRequest);
    notificationService.persistNotification(notificationRequest);
  }

}
