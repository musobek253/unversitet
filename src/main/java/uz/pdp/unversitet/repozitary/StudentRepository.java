package uz.pdp.unversitet.repozitary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.unversitet.entity.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Integer id);

    Page<Student> findAllByGroups_Faculty_UniversityId(Integer groups_faculty_university_id, Pageable pageable);
    Page<Student>findAllByGroups_FacultyId(Integer groups_faculty_id, Pageable pageable);

    Page<Student> findAllByGroupsId(Integer groups_id, Pageable pageable);
}
