package com.webinar.elasticsearch.liquibase;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import liquibase.change.DatabaseChange;
import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.exception.CustomChangeException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@DatabaseChange(name = "bulkInsertToElasticsearch", description = "Insert to Elasticsearch", priority = 1)
@Data
public class ElasticsearchBulkInsertChange implements CustomTaskChange {

  @Autowired
  ElasticsearchClient client1;

  ElasticsearchClient client = SpringContext.getBean(ElasticsearchClient.class);

  private String indexName;
  private String filePath;

  @Override public void execute(Database database) throws CustomChangeException {

    try {
      List<Map<String, Object>> documents = loadDocumentsFromJson(filePath);
      BulkRequest.Builder bulkRequestBuilder  = new BulkRequest.Builder();

      for (Map<String, Object> document : documents) {
        bulkRequestBuilder .operations(op ->
            op.index(i -> i.index(indexName).document(document)));
      }

      BulkRequest bulkRequest = bulkRequestBuilder.build();
      BulkResponse bulkResponse = client.bulk(bulkRequest);

      if (bulkResponse.errors()) {
        throw new CustomChangeException("Bulk insert found errors " + bulkResponse.toString());
      }
    } catch (IOException e) {
      throw new CustomChangeException("Bulk insert failed " + e.getMessage());
    }

  }

  @Override public String getConfirmationMessage() {
    return null;
  }

  @Override public void setUp() throws SetupException {

  }

  @Override public void setFileOpener(ResourceAccessor resourceAccessor) {

  }

  @Override public ValidationErrors validate(Database database) {
    return null;
  }

  private List<Map<String, Object>> loadDocumentsFromJson(String filePath) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    InputStream stream = this.getClass().getResourceAsStream(filePath);
    return mapper.readValue(stream, new TypeReference<List<Map<String, Object>>>() {});
  }
}
