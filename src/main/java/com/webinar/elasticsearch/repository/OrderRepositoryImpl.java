package com.webinar.elasticsearch.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.TopHitsAggregate;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import co.elastic.clients.json.JsonData;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webinar.elasticsearch.model.Product;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom {

  private final ElasticsearchClient client;

  ObjectMapper mapper = new ObjectMapper();

  @Override
  public Product findMostExpensiveBoughtByCustomer(String customerId) {

    SearchRequest request = SearchRequest.of(o -> o
        .index("orders") // st up the index
        .size(0) // we do care about the histogram here
        .query(q -> q
            .term(t -> t
                .field("customerId")
                .value(customerId)
            )
        ) // this the filter to get from a specific customer
        .aggregations("products", a -> a
            .nested(n -> n.path("products"))
            .aggregations("max_expensive", ms -> ms.topHits(th -> th
                .sort(s -> s.field(f -> f.field("products.price").order(SortOrder.Desc)))
                .size(1)
            ))

        )
    );

    try {
      SearchResponse<Void> searchResponse = client.search(request, Void.class);
      TopHitsAggregate mostExpansive = searchResponse.aggregations().get("products")
          .nested().aggregations().get("max_expensive").topHits();
      HitsMetadata<JsonData> metaData = mostExpansive.hits();
      List<Hit<JsonData>> hits = metaData.hits();
      if (!hits.isEmpty()) {
        JsonData productData = hits.get(0).source();
        assert productData != null;
        String jsonString = productData.toJson().toString();
        return mapper.readValue(jsonString, Product.class);
      }
    } catch (IOException e) {
      throw new RuntimeException("Error querying products: " + e.getMessage());
    }

    return null;

  }
}
