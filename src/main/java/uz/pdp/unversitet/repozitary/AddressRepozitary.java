package uz.pdp.unversitet.repozitary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.unversitet.entity.Address;
@Repository
public interface AddressRepozitary extends JpaRepository<Address,Integer> {

    boolean existsByStreet(String street);
}
