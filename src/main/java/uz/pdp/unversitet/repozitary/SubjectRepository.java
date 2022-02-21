package uz.pdp.unversitet.repozitary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.unversitet.entity.Subject;
@Repository
public interface SubjectRepository extends JpaRepository<Subject,Integer> {
    boolean existsByName(String name);
}
