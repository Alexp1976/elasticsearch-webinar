package com.webinar.elasticsearch.dto;

import com.webinar.elasticsearch.model.Customer;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class CustomerDTO {

  private List<Customer> customerMatch = new ArrayList<>();

  private List<Customer> customerTerm = new ArrayList<>();
}
