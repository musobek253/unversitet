package uz.pdp.unversitet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.unversitet.dto.ApiResponse;
import uz.pdp.unversitet.dto.FacultyDTO;
import uz.pdp.unversitet.entity.Faculty;
import uz.pdp.unversitet.entity.Groups;
import uz.pdp.unversitet.repozitary.FacultyRepository;
import uz.pdp.unversitet.repozitary.UnversityRepozitary;

import java.util.Optional;

@Service
public class FacultyService {

    private  final FacultyRepository facultyRepository;
    private final UnversityRepozitary unversityRepozitary;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository, UnversityRepozitary unversityRepozitary) {
        this.facultyRepository = facultyRepository;
        this.unversityRepozitary = unversityRepozitary;
    }

    public ApiResponse addFaculty(FacultyDTO facultyDTO){
        if (facultyRepository.existsByName(facultyDTO.getName()))
            return new ApiResponse("Already exist Faculty",false);
        if (!unversityRepozitary.findById(facultyDTO.getUniversityId()).isPresent())
            return new ApiResponse("University not found",false);
        Faculty faculty = new Faculty();

        faculty.setName(facultyDTO.getName());
        faculty.setUniversity(unversityRepozitary.findById(facultyDTO.getUniversityId()).get());
        facultyRepository.save(faculty);
        return new ApiResponse("Successfully Added",true);
    }

    public ApiResponse editedFaculty(Integer id,FacultyDTO facultyDTO){
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (!optionalFaculty.isPresent())
            return new ApiResponse("Faculty not found",false);
        if (facultyRepository.existsByName(facultyDTO.getName()))
            return new ApiResponse("Already exist Faculty",false);
        if (!unversityRepozitary.findById(facultyDTO.getUniversityId()).isPresent())
            return new ApiResponse("University not found", false);
        Faculty faculty = optionalFaculty.get();
        faculty.setName(facultyDTO.getName());
        faculty.setUniversity(unversityRepozitary.findById(facultyDTO.getUniversityId()).get());
        facultyRepository.save(faculty);
        return new ApiResponse("Successfully edited", true);
    }

    public ApiResponse deletedFaculty(Integer id){
        Optional<Faculty> facultyOptional = facultyRepository.findById(id);
        if (facultyOptional.isPresent()){
            facultyRepository.findById(id);
            return new ApiResponse("Successfully deleted",true);
        }else return new ApiResponse("Faculty not found",false);
    }
}
