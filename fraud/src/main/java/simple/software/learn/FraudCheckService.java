package simple.software.learn;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record FraudCheckService(FraudCheckHistoryRepository repository) {

  public boolean isFraudulentCustomer(Integer customerId) {
    final var obj = FraudCheckHistory.builder()
        .isFraudster(false)
        .customerId(customerId)
        .createdAt(LocalDateTime.now())
        .build();

    repository.save(obj);
    log.info("Saved {} on the database", obj);

    return false;
  }

}
