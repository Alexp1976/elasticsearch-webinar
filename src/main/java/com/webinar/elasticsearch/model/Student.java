package com.webinar.elasticsearch.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(indexName = "students", createIndex = false)
@Data
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Field(name = "name")
  private String name;

  private int age;

  private boolean active;

  private Date dateOfBirth;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private List<String> grades;

}
