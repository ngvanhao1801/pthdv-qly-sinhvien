package hunre.it.student.service;

import hunre.it.student.entity.Student;
import hunre.it.student.exception.DuplicateException;
import hunre.it.student.exception.NotFoundException;
import hunre.it.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
  private final StudentRepository studentRepository;

  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public List<Student> listAll() {
    List<Student> students = studentRepository.findAll();
    if (students.isEmpty()) {
      throw new NotFoundException("Student not found");
    }
    return students;
  }

  public Optional<?> findById(Integer id) {
    Optional<Student> student = studentRepository.findById(id);
    if (!student.isPresent()) {
      throw new NotFoundException("Không tìm thấy student với id: " + id);
    }
    return student;
  }

  public Student save(Student student) {
    if (studentRepository.existsByHoTen(student.getHoTen())) {
      throw new NotFoundException("Tên product đã tồn tại");
    }
    return studentRepository.save(student);
  }

  public Student updateStudent(Integer id, Student student) {
    Optional<Student> existingStudent = studentRepository.findById(id);
    if (!existingStudent.isPresent()) {
      throw new NotFoundException("Không tìm thấy product với id: " + id);
    }
    if (studentRepository.existsByHoTenAndIdNot(student.getHoTen(), id)) {
      throw new DuplicateException("Tên xe đã tồn tại");
    }
    return studentRepository.save(student);
  }

  public void delete(Integer id) {
    if (!studentRepository.existsById(id)) {
      throw new NotFoundException("Không tìm thấy student với id: " + id);
    }
    studentRepository.deleteById(id);
  }
}