package simple.software.learn.notification;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_id_sequence")
  private Integer notificationId;

  private String message;
  private String sender;
  private String toCustomerEmail;
  private String toCustomerId;
  private LocalDateTime sentAt;

}
