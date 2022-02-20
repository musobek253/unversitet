package uz.pdp.unversitet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.unversitet.dto.ApiResponse;
import uz.pdp.unversitet.dto.CountryDTO;
import uz.pdp.unversitet.entity.Continent;
import uz.pdp.unversitet.entity.Country;
import uz.pdp.unversitet.repozitary.ContinentRepozitary;
import uz.pdp.unversitet.repozitary.CountryRepozitary;

import java.util.Optional;

@Service
public class CountryService {

    private final CountryRepozitary countryRepozitary;
    private final ContinentRepozitary continentRepozitary;

    @Autowired
    public CountryService(CountryRepozitary countryRepozitary, ContinentRepozitary continentRepozitary) {
        this.countryRepozitary = countryRepozitary;
        this.continentRepozitary = continentRepozitary;
    }
    public ApiResponse addCountry(CountryDTO countryDTO){
        Country country = new Country();
        if (countryRepozitary.existsByName(countryDTO.getName()))
            return new ApiResponse("Already exist",false);
        if (!continentRepozitary.findById(countryDTO.getContinentId()).isPresent())
            return new ApiResponse("Continent not found",false);
        country.setName(countryDTO.getName());
        country.setContinent(continentRepozitary.findById(countryDTO.getContinentId()).get());
        countryRepozitary.save(country);
        return new ApiResponse("successfully added",true);
    }
    public ApiResponse editCountry(Integer id,CountryDTO countryDTOedit){
        Optional<Country> byId = countryRepozitary.findById(id);
        Country editCountry = byId.get();
        if (countryRepozitary.existsByName(countryDTOedit.getName()))
            return new ApiResponse("Already exist",false);
        if (!continentRepozitary.findById(countryDTOedit.getContinentId()).isPresent())
            return new ApiResponse("Continent not found",false);
        if (!byId.isPresent())
            return new ApiResponse("Not found",false);

        editCountry.setName(countryDTOedit.getName());
        editCountry.setContinent(continentRepozitary.findById(countryDTOedit.getContinentId()).get());
        countryRepozitary.save(editCountry);
        return new ApiResponse("Edit Successfully",true);
    }
}
