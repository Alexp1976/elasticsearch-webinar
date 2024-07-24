package com.webinar.elasticsearch.repository;

import com.webinar.elasticsearch.dto.CustomerDTO;
import com.webinar.elasticsearch.model.Customer;
import java.util.List;

public interface CustomerRepositoryCustom {

  CustomerDTO findCustomerByName(String name);

}
