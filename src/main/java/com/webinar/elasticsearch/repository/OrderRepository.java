package com.webinar.elasticsearch.repository;

import com.webinar.elasticsearch.model.Order;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface OrderRepository
    extends ElasticsearchRepository<Order, String>, OrderRepositoryCustom {
}
