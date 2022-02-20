package uz.pdp.unversitet.controller.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.unversitet.dto.ApiResponse;
import uz.pdp.unversitet.dto.DistrictDTO;
import uz.pdp.unversitet.entity.District;
import uz.pdp.unversitet.repozitary.DistrictRepozitary;
import uz.pdp.unversitet.service.DistrictService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/district")
public class DistrictController {
    @Autowired
    public DistrictController(DistrictRepozitary districtRepozitary, DistrictService districtService) {
        this.districtRepozitary = districtRepozitary;
        this.districtService = districtService;
    }

    private final DistrictRepozitary districtRepozitary;
    private final DistrictService districtService;

    @PostMapping("/add")
    public ApiResponse addDistrict(@RequestBody DistrictDTO districtDTO){
        return districtService.addDistrict(districtDTO);
    }
    @GetMapping("/all")
    public List<District> getAllDistrict(){
        return  districtRepozitary.findAll();
    }
    @GetMapping("/{id}")
    public District getBYDistrict(@PathVariable Integer id){
        Optional<District> districtOptional = districtRepozitary.findById(id);
        return districtOptional.orElseGet(District::new);
    }
    @PutMapping("/{id}")
    public ApiResponse editDistrict(@PathVariable Integer id,@RequestBody DistrictDTO districtDTO){
        return districtService.editDistrict(districtDTO,id);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deletedDistrict(@PathVariable Integer id){
        districtRepozitary.findById(id);
        return new ApiResponse("deleted successfully",true);
    }
}
