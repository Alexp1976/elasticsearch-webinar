package com.webinar.elasticsearch.service;

import com.webinar.elasticsearch.model.Product;
import com.webinar.elasticsearch.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository repository;

  public Product getOrderedProductByMaxPrice(String customerId) {

    return repository.findMostExpensiveBoughtByCustomer(customerId);

  }
}
