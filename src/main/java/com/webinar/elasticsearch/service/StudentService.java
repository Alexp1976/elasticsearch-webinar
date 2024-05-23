package com.webinar.elasticsearch.service;

import com.webinar.elasticsearch.model.Student;
import com.webinar.elasticsearch.repository.StudentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

  private final StudentRepository repository;

  public Student findStudentById(String id) {
    return repository.findById(id).orElse(null);
  }

  public List<Student> findStudentByName(String name) {
    return repository.findByName(name);
  }

  public Iterable<Student> findAllStudents() {
    return repository.findAll();
  }

  public Student saveStudent(Student student) {
    return repository.save(student);
  }

  public List<Student> searchStudent(Student student) {

    return repository.findStudentByNameAndAge(student.getName(), student.getAge());
  }

}
