package com.webinar.elasticsearch.repository;

import com.webinar.elasticsearch.model.Student;
import java.util.List;

public interface StudentRepositoryCustom {

  List<Student> findStudentByNameAndAge(String name, int age);

}
