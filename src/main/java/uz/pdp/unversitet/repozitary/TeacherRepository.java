package uz.pdp.unversitet.repozitary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.unversitet.entity.Teacher;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    boolean existsByName(String name);
    boolean existsByIdIn(List<Integer> ids);// // buni vazifasi siz bergan teacherlarni idlari aynan bizni bazada bor yo'q
    List<Teacher> findAllByIdIn(List<Integer> ids);//  bu vazifasi berilgan idlar listigaga mos teacherlarni  listinin beradi
}
