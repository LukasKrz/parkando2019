package pl.parkando.parkando2019.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.parkando.parkando2019.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}