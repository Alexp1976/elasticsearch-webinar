package com.webinar.elasticsearch.repository;

import com.webinar.elasticsearch.model.Student;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface StudentRepository extends ElasticsearchRepository<Student, String>, StudentRepositoryCustom {

  List<Student> findByName(String name);

}
