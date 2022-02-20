package uz.pdp.unversitet.controller.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.unversitet.dto.AddressDTO;
import uz.pdp.unversitet.dto.ApiResponse;
import uz.pdp.unversitet.entity.Address;
import uz.pdp.unversitet.repozitary.AddressRepozitary;
import uz.pdp.unversitet.service.AddressService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressRepozitary addressRepozitary;
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressRepozitary addressRepozitary, AddressService addressService) {
        this.addressRepozitary = addressRepozitary;
        this.addressService = addressService;
    }

    @PostMapping("/add")
    public ApiResponse addAddress(@RequestBody AddressDTO addressDTO){
        return addressService.addAddress(addressDTO);
    }
    @GetMapping("/all")
    public List<Address> getAllAddress(){
        return  addressRepozitary.findAll();
    }
    @GetMapping("/{id}")
    public Address getBYAddress(@PathVariable Integer id){
        Optional<Address> addressOptional = addressRepozitary.findById(id);
        return addressOptional.orElseGet(Address::new);
    }
    @PutMapping("/{id}")
    public ApiResponse editAddress(@PathVariable Integer id,@RequestBody AddressDTO addressDTO){
        return addressService.editAddress(addressDTO,id);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deletedAddress(@PathVariable Integer id){
        addressRepozitary.findById(id);
        return new ApiResponse("deleted successfully",true);
    }
}
