package uz.pdp.unversitet.repozitary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.unversitet.entity.Teacher;
@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    boolean existsByName(String name);
}
