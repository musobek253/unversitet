package uz.pdp.unversitet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.unversitet.dto.ApiResponse;
import uz.pdp.unversitet.dto.TeacherDTO;
import uz.pdp.unversitet.entity.Teacher;
import uz.pdp.unversitet.repozitary.SubjectRepository;
import uz.pdp.unversitet.repozitary.TeacherRepository;

import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, SubjectRepository subjectRepository) {
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
    }

    public ApiResponse addTeacher(TeacherDTO teacherDTO){
        if (teacherRepository.existsByName(teacherDTO.getName()))
            return new ApiResponse("Already exist Teacher",false);
        if (!subjectRepository.findById(teacherDTO.getSubjectId()).isPresent())
            return new ApiResponse("Subject not found",false);
        Teacher teacher = new Teacher();
        teacher.setName(teacherDTO.getName());
        teacher.setSubject(subjectRepository.findById(teacherDTO.getSubjectId()).get());
        teacherRepository.save(teacher);
        return new ApiResponse("Successfully Added",true);
    }

    public ApiResponse editTeacher(TeacherDTO teacherDTO,Integer id){
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        Teacher teacher = teacherOptional.get();
        if (!teacherOptional.isPresent())
            return new ApiResponse("Teacher not found",false);
        if (teacherRepository.existsByName(teacherDTO.getName()))
            return new ApiResponse("Already exist Teacher",false);
        if (!subjectRepository.findById(teacherDTO.getSubjectId()).isPresent())
            return new ApiResponse("Subject not found",false);
        teacher.setName(teacherDTO.getName());
        teacher.setSubject(subjectRepository.findById(teacherDTO.getSubjectId()).get());
        teacherRepository.save(teacher);
        return new ApiResponse("Successfully edited", true);
    }
    public ApiResponse deletedTeacher(Integer id){
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        if (teacherOptional.isPresent()){
            teacherRepository.deleteById(id);
            return new ApiResponse("Successfully deleted",true);
        }else return new ApiResponse("Teacher Not found",false);
    }
}
