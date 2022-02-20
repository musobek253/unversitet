package uz.pdp.unversitet.controller.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.unversitet.dto.ApiResponse;
import uz.pdp.unversitet.dto.RegionDTO;
import uz.pdp.unversitet.entity.Region;
import uz.pdp.unversitet.repozitary.RegionRepozitary;
import uz.pdp.unversitet.service.RegionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/region")
public class RegionController {

    private final RegionService regionService;
    private final RegionRepozitary regionRepozitary;

    @Autowired
    public RegionController(RegionService regionService, RegionRepozitary regionRepozitary) {
        this.regionService = regionService;
        this.regionRepozitary = regionRepozitary;
    }

    @PostMapping("/add")
    public ApiResponse addRegion(@RequestBody RegionDTO regionDTO){
        return regionService.addRegion(regionDTO);
    }
    @GetMapping("/all")
    public List<Region> getAllRegion(){
        return  regionRepozitary.findAll();
    }
    @GetMapping("/{id}")
    public Region getBYRegion(@PathVariable Integer id){
        Optional<Region> regionOptional = regionRepozitary.findById(id);
        return regionOptional.orElseGet(Region::new);
    }
    @PutMapping("/{id}")
    public ApiResponse editRegion(@PathVariable Integer id,@RequestBody RegionDTO regionDTO){
        return regionService.editRegion(regionDTO,id);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deletedRedion(@PathVariable Integer id){
        regionRepozitary.findById(id);
        return new ApiResponse("deleted successfully",true);
    }
}
