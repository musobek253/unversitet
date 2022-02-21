package uz.pdp.unversitet.repozitary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.unversitet.entity.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
}
