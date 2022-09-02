package simple.software.learn.customer;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("api/v1/customers")
public record CustomerController(CustomerService customerService) {

  @PostMapping
  public void registerCustomer(@RequestBody CustomerRegistrationRequest customerRequest) {
    log.info(() -> String.format("New customer registration {%s}", customerRequest));
    customerService.registerCustomer(customerRequest);
  }
}
