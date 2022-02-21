package uz.pdp.unversitet.controller.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.unversitet.dto.ApiResponse;
import uz.pdp.unversitet.dto.UnversitetDTO;
import uz.pdp.unversitet.entity.University;
import uz.pdp.unversitet.repozitary.UnversityRepozitary;
import uz.pdp.unversitet.service.UniversityService;

import javax.persistence.PersistenceUnit;
import java.util.List;

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

}
