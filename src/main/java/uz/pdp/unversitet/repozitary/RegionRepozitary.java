package uz.pdp.unversitet.repozitary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.unversitet.entity.Region;

@Repository
public interface RegionRepozitary extends JpaRepository<Region,Integer> {
    boolean existsByName(String name);
}
