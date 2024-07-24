package com.webinar.elasticsearch.repository;

import com.webinar.elasticsearch.model.Customer;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CustomerRepository
    extends ElasticsearchRepository<Customer, String>, CustomerRepositoryCustom {

  List<Customer> findByName(String name);

}
