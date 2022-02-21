package uz.pdp.unversitet.repozitary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.unversitet.entity.Groups;

@Repository
public interface GroupsRepository extends JpaRepository<Groups,Integer> {
     boolean existsByName(String name);
}
