package simple.software.learn;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/fraud-check")
@Log4j2
public record FraudCheckController(FraudCheckService checkService) {

  @GetMapping(path = "{customerId}")
  public FraudCheckResponse isFraudster(@PathVariable Integer customerId) {
    final var isFraudulent = checkService.isFraudulentCustomer(customerId);
    log.info(() -> "Fraud check request for customer with id " + customerId);
    return new FraudCheckResponse(isFraudulent);
  }
}
