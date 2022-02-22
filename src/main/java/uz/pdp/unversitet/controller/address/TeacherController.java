package uz.pdp.unversitet.controller.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.unversitet.dto.ApiResponse;
import uz.pdp.unversitet.dto.TeacherDTO;
import uz.pdp.unversitet.entity.Teacher;
import uz.pdp.unversitet.repozitary.TeacherRepository;
import uz.pdp.unversitet.service.TeacherService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherRepository teacherRepository;
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherRepository teacherRepository, TeacherService teacherService) {
        this.teacherRepository = teacherRepository;
        this.teacherService = teacherService;
    }

    @GetMapping("/all")
    public List<Teacher> getTeacherAll(){
        return teacherRepository.findAll();
    }
    @GetMapping("/{id}")
    public Teacher getByIdteacher(@PathVariable Integer id){
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        return optionalTeacher.orElseGet(Teacher::new);
    }
    @PostMapping("/add")
    public ApiResponse addTeacher(@RequestBody TeacherDTO teacherDTO){
        return teacherService.addTeacher(teacherDTO);
    }

    @PutMapping("/{id}")
    public ApiResponse editTeacher(@RequestBody TeacherDTO teacherDTO,@PathVariable Integer id){
        return teacherService.editTeacher(teacherDTO, id);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deletedByIdTeacher(@PathVariable Integer id){
        return teacherService.deletedTeacher(id);
    }

}
