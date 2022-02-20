package uz.pdp.unversitet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.unversitet.dto.ApiResponse;
import uz.pdp.unversitet.dto.RegionDTO;
import uz.pdp.unversitet.entity.Region;
import uz.pdp.unversitet.repozitary.CountryRepozitary;
import uz.pdp.unversitet.repozitary.RegionRepozitary;

import java.util.Optional;

@Service
public class RegionService {

    private final RegionRepozitary regionRepozitary;
    private  final CountryRepozitary countryRepozitary;

    @Autowired
    public RegionService(RegionRepozitary regionRepozitary, CountryRepozitary countryRepozitary) {
        this.regionRepozitary = regionRepozitary;
        this.countryRepozitary = countryRepozitary;
    }

    public ApiResponse addRegion(RegionDTO regionDTO){
        Region region = new Region();
        if (regionRepozitary.existsByName(regionDTO.getName()))
            return new ApiResponse("Already exist",false);
        if (!countryRepozitary.findById(regionDTO.getCountryId()).isPresent())
            return new ApiResponse("not found country",false);

        region.setName(regionDTO.getName());
        region.setCountry(countryRepozitary.findById(regionDTO.getCountryId()).get());
        regionRepozitary.save(region);
        return new ApiResponse("Successfully Added" ,true);
    }

    /**
     *
     * @param regionDTO
     * @param id
     * @return ApiResponse  xabar qaytaradi yani qo'shildi qo'shilmadi o'zgardi o'zgarmadi
     */

    public  ApiResponse editRegion (RegionDTO regionDTO ,Integer id){
        Optional<Region> regionOptional = regionRepozitary.findById(id);
        Region region = regionOptional.get();
        if (!regionOptional.isPresent())
            return new ApiResponse("Not found Region",false);
        if (regionRepozitary.existsByName(regionDTO.getName()))
            return  new ApiResponse("Already exist", false);
        if (!countryRepozitary.findById(regionDTO.getCountryId()).isPresent())
            return new ApiResponse("Not found Country",false);
        region.setName(regionDTO.getName());
        region.setCountry(countryRepozitary.findById(regionDTO.getCountryId()).get());
        regionRepozitary.save(region);
        return new ApiResponse("Edit successfully", true);
    }
}
