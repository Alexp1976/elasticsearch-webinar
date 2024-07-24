package com.webinar.elasticsearch.web;

import com.webinar.elasticsearch.model.Product;
import com.webinar.elasticsearch.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @GetMapping("/max-price/{customerId}")
  public ResponseEntity<Product> findMostExpansiveProductBought(@PathVariable("customerId") String customerId) {
    return ResponseEntity.ok(orderService.getOrderedProductByMaxPrice(customerId));
  }
}
