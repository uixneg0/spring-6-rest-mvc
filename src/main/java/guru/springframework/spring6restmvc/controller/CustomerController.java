package guru.springframework.spring6restmvc.controller;

import guru.springframework.spring6restmvc.model.Beer;
import guru.springframework.spring6restmvc.model.Customer;
import guru.springframework.spring6restmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PutMapping("{id}")
    public ResponseEntity editCustomerById(@PathVariable UUID id, @RequestBody Customer customer){
        Customer savedCustomer = customerService.updateCustomer(id, customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "api/v1/customer/" + savedCustomer.getUuid().toString());
        return new ResponseEntity(headers, HttpStatus.NO_CONTENT);
    }

    @PostMapping()
    public ResponseEntity addCustomer(@RequestBody Customer customer){
        Customer savedCustomer = customerService.saveNewCustomer(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "api/v1/customer/" + savedCustomer.getUuid().toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Customer> listCustomers(){
        return customerService.listCustomers();
    }


    @GetMapping("{id}")
    public Customer getCustomerById(@PathVariable("id") UUID id){
        log.debug("Get customer by Id - in controller");
        return customerService.getCustomerByUUID(id);
    }
}
