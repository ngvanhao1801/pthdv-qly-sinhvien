package hunre.it.student.repository;

import hunre.it.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer>  {

  boolean existsByHoTen(String hoTen);

  boolean existsById(Integer id);

  boolean existsByHoTenAndIdNot(String hoTen, Integer id);

}