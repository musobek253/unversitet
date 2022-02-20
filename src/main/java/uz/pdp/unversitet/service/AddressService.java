package uz.pdp.unversitet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.unversitet.dto.AddressDTO;
import uz.pdp.unversitet.dto.ApiResponse;
import uz.pdp.unversitet.entity.Address;
import uz.pdp.unversitet.repozitary.AddressRepozitary;
import uz.pdp.unversitet.repozitary.DistrictRepozitary;
import java.util.Optional;

@Service
public class AddressService {
    private  final AddressRepozitary addressRepozitary;
    private final DistrictRepozitary districtRepozitary;

    @Autowired
    public AddressService(AddressRepozitary addressRepozitary, DistrictRepozitary districtRepozitary) {
        this.addressRepozitary = addressRepozitary;
        this.districtRepozitary = districtRepozitary;
    }

    public ApiResponse addAddress(AddressDTO addressDTO){
        Address address = new Address();
        if (addressRepozitary.existsByStreet(addressDTO.getStreet()))
            return  new ApiResponse("Already exist",false);
        if (!districtRepozitary.findById(addressDTO.getDistrictId()).isPresent())
            return new ApiResponse("District not found",false);

        address.setStreet(addressDTO.getStreet());
        address.setDistrict(districtRepozitary.findById(addressDTO.getDistrictId()).get());
        address.setHouseNumber(addressDTO.getHouseNumber());
        addressRepozitary.save(address);
        return  new ApiResponse("Successfully Added",true);
    }

    public ApiResponse editAddress(AddressDTO addressDTO, Integer id){
        Optional<Address> optionalAddress = addressRepozitary.findById(id);
        Address address = optionalAddress.get();

        if (!optionalAddress.isPresent())
            return new ApiResponse("Address not found", false);
        if (districtRepozitary.existsByName(addressDTO.getStreet()))
            return new ApiResponse("Already exist", false);
        if (!districtRepozitary.findById(addressDTO.getDistrictId()).isPresent())
            return new ApiResponse("Region not found",false);

        address.setStreet(addressDTO.getStreet());
        address.setDistrict(districtRepozitary.findById(addressDTO.getDistrictId()).get());
        address.setHouseNumber(addressDTO.getHouseNumber());
        addressRepozitary.save(address);
        return new ApiResponse("Edited successfully",true);
    }
}
