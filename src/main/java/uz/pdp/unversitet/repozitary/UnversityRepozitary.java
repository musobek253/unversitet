package uz.pdp.unversitet.repozitary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.unversitet.entity.University;
@Repository
public interface UnversityRepozitary extends JpaRepository<University,Integer> {
    boolean existsByName(String name);
}
