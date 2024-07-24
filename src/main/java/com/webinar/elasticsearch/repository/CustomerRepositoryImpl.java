package com.webinar.elasticsearch.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.webinar.elasticsearch.dto.CustomerDTO;
import com.webinar.elasticsearch.model.Customer;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

  private final ElasticsearchClient client;


  @Override
  public CustomerDTO findCustomerByName(String name) {

    CustomerDTO response = new CustomerDTO();

    response.setCustomerMatch(findUsingMatch(name));
    response.setCustomerTerm(findUsingTerm(name));

    return response;

  }

  private List<Customer> findUsingMatch(String name) {

    MatchQuery query = MatchQuery.of(m -> m
        .field("name")
        .query(name)
    );

    SearchRequest request = SearchRequest.of(s -> s
        .index("customers")
        .query(q -> q.match(query))
    );

    try {
      SearchResponse<Customer> response = client.search(request, Customer.class);
      return response.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private List<Customer> findUsingTerm(String name) {

    TermQuery query = TermQuery.of(t -> t
        .field("name.keyword")
        .value(name)
    );

    SearchRequest request = SearchRequest.of(s -> s
        .index("customers")
        .query(q -> q.term(query))
    );


    try {
      SearchResponse<Customer> response = client.search(request, Customer.class);
      return response.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
