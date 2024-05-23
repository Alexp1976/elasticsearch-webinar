package com.webinar.elasticsearch.web;

import com.webinar.elasticsearch.model.Student;
import com.webinar.elasticsearch.service.StudentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

  private final StudentService service;

  @GetMapping("/{id}")
  public ResponseEntity<Student> getStudentById(@PathVariable("id") String id) {
    return ResponseEntity.ok(service.findStudentById(id));
  }

  @PostMapping
  public ResponseEntity<Student> addStudent(@RequestBody Student student) {
    return ResponseEntity.ok(service.saveStudent(student));
  }

  @GetMapping("/search/name/{name}")
  public ResponseEntity<List<Student>> getStudentByName(@PathVariable("name") String name) {
    return ResponseEntity.ok(service.findStudentByName(name));
  }

  @GetMapping("/list")
  public ResponseEntity<Iterable<Student>> getAllStudents() {
    return ResponseEntity.ok(service.findAllStudents());
  }

  @PostMapping("/search")
  public ResponseEntity<List<Student>> searchStudent(@RequestBody Student student) {
    return ResponseEntity.ok(service.searchStudent(student));
  }

}
