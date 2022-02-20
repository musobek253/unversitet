package uz.pdp.unversitet.repozitary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.unversitet.entity.District;

@Repository
public interface DistrictRepozitary extends JpaRepository<District,Integer> {
    boolean existsByName(String name);

}
