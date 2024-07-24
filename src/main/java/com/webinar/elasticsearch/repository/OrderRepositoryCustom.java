package com.webinar.elasticsearch.repository;

import com.webinar.elasticsearch.model.Product;

public interface OrderRepositoryCustom {

  Product findMostExpensiveBoughtByCustomer(String customerId);

}
