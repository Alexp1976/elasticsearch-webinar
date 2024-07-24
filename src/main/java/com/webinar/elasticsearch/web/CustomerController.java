package com.webinar.elasticsearch.web;

import com.webinar.elasticsearch.dto.CustomerDTO;
import com.webinar.elasticsearch.model.Customer;
import com.webinar.elasticsearch.service.CustomerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService service;

  @GetMapping("/{id}")
  public ResponseEntity<Customer> getCustomerById(@PathVariable("id") String id) {
    return ResponseEntity.ok(service.findCustomerById(id));
  }

  @PostMapping
  public ResponseEntity<Customer> addCustomer(@RequestBody Customer student) {
    return ResponseEntity.ok(service.saveCustomer(student));
  }

  @GetMapping("/search/name/{name}")
  public ResponseEntity<List<Customer>> getCustomerByName(@PathVariable("name") String name) {
    return ResponseEntity.ok(service.findCustomerByName(name));
  }

  @GetMapping("/list")
  public ResponseEntity<Iterable<Customer>> getAllCustomers() {
    return ResponseEntity.ok(service.findAllCustomers());
  }

  @PostMapping("/search/term-query")
  public ResponseEntity<CustomerDTO> searchCustomer(@RequestBody Customer customer) {
    return ResponseEntity.ok(service.searchCustomer(customer));
  }

}
