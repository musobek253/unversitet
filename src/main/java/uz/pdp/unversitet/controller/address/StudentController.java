package uz.pdp.unversitet.controller.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.unversitet.dto.ApiResponse;
import uz.pdp.unversitet.dto.StudentDTO;
import uz.pdp.unversitet.entity.Student;
import uz.pdp.unversitet.repozitary.StudentRepository;
import uz.pdp.unversitet.service.StudentService;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    private  final StudentService studentService;
    private final StudentRepository studentRepository;
    @Autowired
    public StudentController( StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/all")
    public List<Student> getStudentAll(){
        return studentRepository.findAll();
    }
    @GetMapping("/{id}")
    public Student getByIdStudent(@PathVariable Integer id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        return optionalStudent.orElseGet(Student::new);
    }
    @PostMapping("/add")
    public ApiResponse addStudent(@RequestBody StudentDTO studentDTO){
        return studentService.addStudent(studentDTO);
    }

    @PutMapping("/{id}")
    public ApiResponse editStudent(@RequestBody StudentDTO studentDTO,@PathVariable Integer id){
        return studentService.editedStudent(id, studentDTO);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deletedByIdStudent(@PathVariable Integer id){
        return studentService.deletedStudent(id);
    }
}
