package uz.pdp.unversitet.controller.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.unversitet.dto.ApiResponse;
import uz.pdp.unversitet.dto.UnversitetDTO;
import uz.pdp.unversitet.entity.University;
import uz.pdp.unversitet.repozitary.UnversityRepozitary;
import uz.pdp.unversitet.service.UniversityService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/university")
public class UniversityController {

    private final UnversityRepozitary unversityRepozitary;
    private final UniversityService universityService;

    @Autowired
    public UniversityController(UnversityRepozitary unversityRepozitary, UnversityRepozitary unversityRepozitary1, UniversityService universityService) {
        this.unversityRepozitary = unversityRepozitary;
        this.universityService = universityService;
    }

    @PostMapping("/add")
    public ApiResponse addUniversity(@RequestBody UnversitetDTO unversitetDTO){
        return universityService.addUniversity(unversitetDTO);
    }

    @PutMapping("/{id}")
    public ApiResponse editUniversity(@RequestBody UnversitetDTO unversitetDTO, @PathVariable Integer id){
        return universityService.editUniversity(unversitetDTO, id);
    }
    @GetMapping("/all")
    public List<University> getUNiversity(){
        return unversityRepozitary.findAll();
    }

    @GetMapping("/{id}")
    public University getByIdUniversity(@PathVariable Integer id){
        Optional<University> optionalUniversity = unversityRepozitary.findById(id);
        return optionalUniversity.orElseGet(University::new);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deletedUniversity(@PathVariable Integer id){
        unversityRepozitary.deleteById(id);
        return new ApiResponse("Successfully deleted",true);
    }

}
