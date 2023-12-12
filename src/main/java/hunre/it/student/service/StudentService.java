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
      throw new NotFoundException("Không tồn tại sinh viên nào");
    }
    return students;
  }

  public Optional<?> findById(Integer id) {
    Optional<Student> student = studentRepository.findById(id);
    if (!student.isPresent()) {
      throw new NotFoundException("Không tìm thấy sinh viên với id: " + id);
    }
    return student;
  }

  public Student save(Student student) {
    if (studentRepository.existsByHoTen(student.getHoTen())) {
      throw new NotFoundException("Tên sinh viên đã tồn tại");
    }
    return studentRepository.save(student);
  }

  public Student updateStudent(Integer id, Student student) {
    Optional<Student> existingStudent = studentRepository.findById(id);
    if (!existingStudent.isEmpty()) {
      throw new NotFoundException("Không tìm thấy sinh viên với id: " + id);
    }
    if (studentRepository.existsByHoTenAndIdNot(student.getHoTen(), id)) {
      throw new DuplicateException("Tên sinh viên đã tồn tại");
    }
    return studentRepository.save(student);
  }

  public void deleteStudent(Integer id) {
    if (!studentRepository.existsById(id)) {
      throw new NotFoundException("Không tìm thấy sinh viên với id: " + id);
    }
    studentRepository.deleteById(id);
  }
}