package uz.pdp.unversitet.repozitary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.unversitet.entity.Country;
@Repository
public interface CountryRepozitary extends JpaRepository<Country,Integer> {
    boolean existsByName(String name);
}
