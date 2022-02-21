package uz.pdp.unversitet.repozitary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.unversitet.entity.Faculty;
@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Integer> {
    boolean existsByName(String name);
}
