package uz.pdp.unversitet.controller.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.unversitet.dto.ApiResponse;
import uz.pdp.unversitet.dto.CountryDTO;
import uz.pdp.unversitet.entity.Country;
import uz.pdp.unversitet.repozitary.CountryRepozitary;
import uz.pdp.unversitet.service.CountryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/country")
public class CountryController {

    private final CountryRepozitary countryRepozitary;
    private final CountryService countryService;

    @Autowired
    public CountryController(CountryRepozitary countryRepozitary, CountryService countryService) {
        this.countryRepozitary = countryRepozitary;
        this.countryService = countryService;
    }
    @PostMapping("/add")
    public ApiResponse addCountry(@RequestBody CountryDTO countryDTO){
         return countryService.addCountry(countryDTO);
    }
    @GetMapping("/all")
    public List<Country> getAllCountry(){
        return  countryRepozitary.findAll();
    }
    @GetMapping("/{id}")
    public Country getBYCountry(@PathVariable Integer id){
        Optional<Country> countryOptional = countryRepozitary.findById(id);
        return countryOptional.orElseGet(Country::new);
    }
    @PutMapping("/{id}")
    public ApiResponse editCountry(@PathVariable Integer id, @RequestBody CountryDTO countryDTO){
       return countryService.editCountry(id, countryDTO);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deletedCountry(@PathVariable Integer id){
        countryRepozitary.findById(id);
        return new ApiResponse("deleted successfully",true);
    }

}
