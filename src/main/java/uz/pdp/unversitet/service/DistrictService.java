package uz.pdp.unversitet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.unversitet.dto.ApiResponse;
import uz.pdp.unversitet.dto.DistrictDTO;
import uz.pdp.unversitet.entity.District;
import uz.pdp.unversitet.repozitary.DistrictRepozitary;
import uz.pdp.unversitet.repozitary.RegionRepozitary;

import java.util.Optional;

@Service
public class DistrictService {

    private  final RegionRepozitary regionRepozitary;
    private final DistrictRepozitary districtRepozitary;

    @Autowired
    public DistrictService(RegionRepozitary regionRepozitary, DistrictRepozitary districtRepozitary) {
        this.regionRepozitary = regionRepozitary;
        this.districtRepozitary = districtRepozitary;
    }

    public ApiResponse addDistrict(DistrictDTO districtDTO){
        District district = new District();
        if (districtRepozitary.existsByName(districtDTO.getName()))
            return  new ApiResponse("Already exist",false);
        if (!regionRepozitary.findById(districtDTO.getRegionId()).isPresent())
            return new ApiResponse("Region not found",false);

        district.setName(districtDTO.getName());
        district.setRegion(regionRepozitary.findById(districtDTO.getRegionId()).get());
        districtRepozitary.save(district);
        return  new ApiResponse("Successfully Added",true);
    }

    public ApiResponse editDistrict(DistrictDTO districtDTO, Integer id){
        Optional<District> optionalDistrict = districtRepozitary.findById(id);
        District district = optionalDistrict.get();

        if (!optionalDistrict.isPresent())
            return new ApiResponse("District not found", false);
        if (districtRepozitary.existsByName(districtDTO.getName()))
            return new ApiResponse("Already exist", false);
        if (!regionRepozitary.findById(districtDTO.getRegionId()).isPresent())
            return new ApiResponse("Region not found",false);

        district.setName(districtDTO.getName());
        district.setRegion(regionRepozitary.findById(districtDTO.getRegionId()).get());
        districtRepozitary.save(district);
        return new ApiResponse("Edited successfully",true);
    }
}
