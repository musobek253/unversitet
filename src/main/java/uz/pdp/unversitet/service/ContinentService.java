package uz.pdp.unversitet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.unversitet.dto.ApiResponse;
import uz.pdp.unversitet.entity.Continent;
import uz.pdp.unversitet.repozitary.ContinentRepozitary;

import java.util.Optional;

@Service
public class ContinentService {

    private final ContinentRepozitary continentRepozitary;
    @Autowired
    public ContinentService(ContinentRepozitary continentRepozitary) {
        this.continentRepozitary = continentRepozitary;
    }

    public ApiResponse addContinent (Continent continent){
        if (continentRepozitary.existsByName(continent.getName()))
            return new ApiResponse("Already exist", false);
        continentRepozitary.save(continent);
        return new ApiResponse("successfully added", true);
    }

    public ApiResponse editContinent(Integer id, Continent continent){
        Optional<Continent> optionalContinent = continentRepozitary.findById(id);
        Continent continent1 = optionalContinent.get();
        if (!optionalContinent.isPresent())
            return new ApiResponse("Not found",false);
        if (continentRepozitary.existsByName(continent.getName()))
            return new ApiResponse("Already exist",false);
        continent1.setName(continent.getName());
        continentRepozitary.save(continent1);
        return new ApiResponse("Edit succsessfuly",true);
    }
}
