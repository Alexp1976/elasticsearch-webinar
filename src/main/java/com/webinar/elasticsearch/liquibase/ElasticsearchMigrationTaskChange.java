package com.webinar.elasticsearch.liquibase;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import java.io.IOException;
import java.io.InputStream;
import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.exception.CustomChangeException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;
import org.springframework.stereotype.Service;

@Service
public class ElasticsearchMigrationTaskChange implements CustomTaskChange {

  private String indexName;
  private String filePath;

  public String getIndexName() {
    return indexName;
  }

  public void setIndexName(String indexName) {
    this.indexName = indexName;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  @Override
  public void execute(Database database) throws CustomChangeException {

    // We need to get the bean from the context as liquibase doesn't support Spring Dependency Injection so far
    ElasticsearchClient client = SpringContext.getBean(ElasticsearchClient.class);

    try {
      InputStream stream = this.getClass().getResourceAsStream(filePath);
      CreateIndexRequest request = CreateIndexRequest.of(b -> b.index(indexName).withJson(stream));
      boolean created = client.indices().create(request).acknowledged();
      if (created) {
        System.out.println("Index created successfully.");
      } else {
        System.out.println("Index creation failed");
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
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
}
