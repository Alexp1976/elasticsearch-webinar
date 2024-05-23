package com.webinar.elasticsearch.repository;

import com.webinar.elasticsearch.model.Student;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;

@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepositoryCustom {

  private final ElasticsearchOperations operations;

  @Override
  public List<Student> findStudentByNameAndAge(String name, int age) {

    Criteria criteria = new Criteria("name").is(name).and("age").is(age);

    Query searchQuery = new CriteriaQuery(criteria);

    SearchHits<Student> hits = operations.search(searchQuery, Student.class);

    return hits.stream()
        .map(SearchHit::getContent)
        .collect(Collectors.toList());
  }
}
