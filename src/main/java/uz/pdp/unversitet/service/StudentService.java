package uz.pdp.unversitet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.unversitet.dto.ApiResponse;
import uz.pdp.unversitet.dto.StudentDTO;
import uz.pdp.unversitet.entity.Student;
import uz.pdp.unversitet.repozitary.GroupsRepository;
import uz.pdp.unversitet.repozitary.StudentRepository;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupsRepository groupsRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, GroupsRepository groupsRepository) {
        this.studentRepository = studentRepository;
        this.groupsRepository = groupsRepository;
    }

    public ApiResponse addStudent(StudentDTO studentDTO){
        if (studentRepository.existsByPhoneNumber(studentDTO.getPhoneNumber()))
            return new ApiResponse("Already exist Student",false);
        if (!groupsRepository.findById(studentDTO.getGroupsId()).isPresent())
            return new ApiResponse("Groups not found" ,false);
        Student student = new Student();
        student.setLastName(studentDTO.getLastName());
        student.setFirstName(studentDTO.getFirstName());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        student.setGroups(groupsRepository.findById(studentDTO.getGroupsId()).get());
        studentRepository.save(student);
        return new ApiResponse("Successfully Added",true);
    }

    public ApiResponse editedStudent(Integer id, StudentDTO studentDTO){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (!optionalStudent.isPresent())
            return new ApiResponse("Student not found",false);
        if (studentRepository.existsByPhoneNumber(studentDTO.getPhoneNumber()))
            return new ApiResponse("Already exist Faculty",false);
        if (!groupsRepository.findById(studentDTO.getGroupsId()).isPresent())
            return new ApiResponse("Groups not found", false);
        Student student = optionalStudent.get();
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        student.setGroups(groupsRepository.findById(studentDTO.getGroupsId()).get());
        studentRepository.save(student);
        return new ApiResponse("Successfully edited", true);
    }

    public ApiResponse deletedStudent(Integer id){
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()){
            studentRepository.findById(id);
            return new ApiResponse("Successfully deleted",true);
        }else return new ApiResponse("Student not found",false);
    }


}
