package uz.pdp.unversitet.controller.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.unversitet.dto.ApiResponse;
import uz.pdp.unversitet.entity.Subject;
import uz.pdp.unversitet.repozitary.SubjectRepository;
import uz.pdp.unversitet.service.SubjectService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class SubjectController {

    private  final SubjectRepository subjectRepository;
    private  final SubjectService subjectService;
    @Autowired
    public SubjectController(SubjectRepository subjectRepository, SubjectService subjectService) {
        this.subjectRepository = subjectRepository;
        this.subjectService = subjectService;
    }
    @PostMapping("/add")
    public ApiResponse addSubject(@RequestBody Subject subject){
        return subjectService.addSubject(subject);
    }

    @PutMapping("/{id}")
    public ApiResponse editSubject(@PathVariable Integer id,@RequestBody Subject subject){
        return subjectService.editedSubject(id, subject);
    }

    @GetMapping("/all")
    public List<Subject> getSubject(){
        return subjectRepository.findAll();
    }
    @GetMapping("/{id}")
    public Subject getByIdSubject(@PathVariable Integer id){
        Optional<Subject> subjectOptional = subjectRepository.findById(id);
        return subjectOptional.orElseGet(Subject::new);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deletedSubject(@PathVariable Integer id){
        return subjectService.deleteSubject(id);
    }


}
