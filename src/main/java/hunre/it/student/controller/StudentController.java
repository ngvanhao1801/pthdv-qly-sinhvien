package hunre.it.student.controller;

import hunre.it.student.entity.Student;
import hunre.it.student.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }


  @GetMapping("/students")
  public List<?> list() {

    return studentService.listAll();
  }

  @GetMapping("/students/{id}")
  public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
    Optional<?> student = studentService.findById(id);

    return ResponseEntity.ok(student);
  }

  @PostMapping("/students")
  public ResponseEntity<?> createNewProduct(@RequestBody Student student) {

    return new ResponseEntity<>(studentService.save(student), HttpStatus.OK);
  }

  @PutMapping("/students/{id}")
  public ResponseEntity<?> updateStudent(@PathVariable Integer id, @RequestBody Student student) {
    Student updatedStudent = studentService.updateStudent(id, student);

    return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
  }

  @DeleteMapping("/students/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
    studentService.delete(id);

    return new ResponseEntity<>("Đã xóa thành công", HttpStatus.OK);
  }
}