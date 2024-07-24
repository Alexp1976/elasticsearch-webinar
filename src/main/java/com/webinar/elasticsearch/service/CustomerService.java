package com.webinar.elasticsearch.service;

import com.webinar.elasticsearch.dto.CustomerDTO;
import com.webinar.elasticsearch.model.Customer;
import com.webinar.elasticsearch.repository.CustomerRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository repository;

  public Customer findCustomerById(String id) {
    return repository.findById(id).orElse(null);
  }

  public List<Customer> findCustomerByName(String name) {
    return repository.findByName(name);
  }

  public Iterable<Customer> findAllCustomers() {
    return repository.findAll();
  }

  public Customer saveCustomer(Customer customer) {
    return repository.save(customer);
  }

  public CustomerDTO searchCustomer(Customer customer) {

    return repository.findCustomerByName(customer.getName());
  }

}
