package uz.pdp.unversitet.controller.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.unversitet.dto.ApiResponse;
import uz.pdp.unversitet.entity.Continent;
import uz.pdp.unversitet.repozitary.ContinentRepozitary;
import uz.pdp.unversitet.service.ContinentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/continent")
public class ContinentController {

    private final ContinentService continentService;
    private final ContinentRepozitary continentRepozitary;

    @Autowired
    public ContinentController(ContinentService continentService, ContinentRepozitary continentRepozitary) {
        this.continentService = continentService;
        this.continentRepozitary = continentRepozitary;
    }

    @PostMapping("/add")
    public ApiResponse addContinent(@RequestBody Continent continent){

        return continentService.addContinent(continent);
    }
    @GetMapping("/all")
    public List<Continent> getContinent(){
         return continentRepozitary.findAll();
    }
    @GetMapping("/all/{id}")
    public Continent getIdContinent(@PathVariable Integer id){
        Optional<Continent> continentOptional = continentRepozitary.findById(id);
        return continentOptional.orElseGet(Continent::new);
    }
    @PutMapping("/{id}")
    public ApiResponse editContinent(@PathVariable Integer id, @RequestBody Continent continent){
        return continentService.editContinent(id, continent);
    }
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        continentRepozitary.deleteById(id);
        return new ApiResponse("Deleted Successfully",true);
    }
}

