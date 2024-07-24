package com.webinar.elasticsearch.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.Instant;
import java.util.List;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "orders", createIndex = false)
@Data
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private Instant orderDate;

  private String customerId;

  private List<Product> products;
}
