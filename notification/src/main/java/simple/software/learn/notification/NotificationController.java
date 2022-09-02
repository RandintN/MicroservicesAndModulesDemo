package simple.software.learn.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simple.software.learn.clients.notification.NotificationRequest;

@Slf4j
@RestController
@RequestMapping("/api/v1/notification")
public record NotificationController(NotificationService notificationService) {

  @PostMapping
  public void sendNotification(@RequestBody NotificationRequest request) {
    log.info("New notification... {}", request);
    notificationService.persistNotification(request);
  }
}
