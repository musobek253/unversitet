package uz.pdp.unversitet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.unversitet.dto.ApiResponse;
import uz.pdp.unversitet.dto.UnversitetDTO;
import uz.pdp.unversitet.entity.Address;
import uz.pdp.unversitet.entity.University;
import uz.pdp.unversitet.repozitary.AddressRepozitary;
import uz.pdp.unversitet.repozitary.DistrictRepozitary;
import uz.pdp.unversitet.repozitary.UnversityRepozitary;

import java.util.Optional;

@Service
public class UniversityService {

    private final UnversityRepozitary unversityRepozitary;
    private final AddressRepozitary addressRepozitary;
    private final DistrictRepozitary districtRepozitary;
    @Autowired
    public UniversityService(UnversityRepozitary unversityRepozitary, AddressRepozitary addressRepozitary, DistrictRepozitary districtRepozitary) {
        this.unversityRepozitary = unversityRepozitary;
        this.addressRepozitary = addressRepozitary;
        this.districtRepozitary = districtRepozitary;
    }

    public ApiResponse addUniversity(UnversitetDTO unversitetDTO){
        Address address = new Address();
        if (addressRepozitary.existsByStreetAndHouseNumberAndDistrict_Id(unversitetDTO.getStreet(),unversitetDTO.getHouseNumber(),unversitetDTO.getDistrictId()))
            return new ApiResponse("Already exists Address!", false);
        if (!districtRepozitary.findById(unversitetDTO.getDistrictId()).isPresent())
            return new ApiResponse("District Not found!", false);
        if (unversityRepozitary.existsByName(unversitetDTO.getName()))
            return new ApiResponse("Already exists University",false);
        address.setStreet(unversitetDTO.getStreet());
        address.setDistrict(districtRepozitary.findById(unversitetDTO.getDistrictId()).get());
        address.setHouseNumber(unversitetDTO.getHouseNumber());
        Address address1 = addressRepozitary.save(address);
        University university = new University();

        university.setAddress(address1);
        university.setName(unversitetDTO.getName());
        unversityRepozitary.save(university);
        return new ApiResponse("Sucessfully Added", true);
    }

    public ApiResponse editUniversity(UnversitetDTO unversitetDTO, Integer id){
        Optional<University> universityOptional = unversityRepozitary.findById(id);
        University university = universityOptional.get();
        if (!universityOptional.isPresent())
            return new ApiResponse("University not found", false);
        if (!districtRepozitary.findById(unversitetDTO.getDistrictId()).isPresent())
            return new ApiResponse("District Not found!", false);
        Optional<Address> addressOptional = addressRepozitary.findById(university.getAddress().getId());
        Address address = addressOptional.get();
        address.setStreet(unversitetDTO.getStreet());
        address.setHouseNumber(unversitetDTO.getHouseNumber());
        address.setDistrict(districtRepozitary.findById(unversitetDTO.getDistrictId()).get());
        Address address1 = addressRepozitary.save(address);
        university.setName(unversitetDTO.getName());
        university.setAddress(address1);
        return new ApiResponse("Successesfully edited", true);

    }
}