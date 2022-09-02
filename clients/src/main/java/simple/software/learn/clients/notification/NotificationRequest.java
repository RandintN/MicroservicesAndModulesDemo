package simple.software.learn.clients.notification;

public record NotificationRequest(Integer notificationId,
                                  String toCustomerEmail,
                                  String message) {
}
