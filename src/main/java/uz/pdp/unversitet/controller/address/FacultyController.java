package uz.pdp.unversitet.controller.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.unversitet.dto.ApiResponse;
import uz.pdp.unversitet.dto.FacultyDTO;
import uz.pdp.unversitet.entity.Faculty;
import uz.pdp.unversitet.repozitary.FacultyRepository;
import uz.pdp.unversitet.service.FacultyService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyRepository facultyRepository;
    private final FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyRepository facultyRepository, FacultyService facultyService) {
        this.facultyRepository = facultyRepository;
        this.facultyService = facultyService;
    }
    @GetMapping("/all")
    public List<Faculty> getFacultyAll(){
        return facultyRepository.findAll();
    }
    @GetMapping("/{id}")
    public Faculty getByIdFaculty(@PathVariable Integer id){
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        return optionalFaculty.orElseGet(Faculty::new);
    }
    @PostMapping("/add")
    public ApiResponse addFaculty(@RequestBody FacultyDTO facultyDTO){
        return facultyService.addFaculty(facultyDTO);
    }

    @PutMapping("/{id}")
    public ApiResponse editFaculty(@RequestBody FacultyDTO facultyDTO,@PathVariable Integer id){
        return facultyService.editedFaculty(id, facultyDTO);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deletedByIdFaculty(@PathVariable Integer id){
        return facultyService.deletedFaculty(id);
    }
}
