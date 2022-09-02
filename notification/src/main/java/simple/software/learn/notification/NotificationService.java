package simple.software.learn.notification;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import simple.software.learn.clients.notification.NotificationRequest;

@Service
public record NotificationService(NotificationRepository repository) {

  public void persistNotification(NotificationRequest request) {
    repository.save(Notification.builder()
        .sender("Robson Cassiano")
        .sentAt(LocalDateTime.now())
        .message(request.message())
        .toCustomerEmail(request.toCustomerEmail())
        .build());
  }

}
