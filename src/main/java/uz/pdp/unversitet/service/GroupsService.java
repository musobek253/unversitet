package uz.pdp.unversitet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.unversitet.dto.ApiResponse;
import uz.pdp.unversitet.dto.GroupsDTO;
import uz.pdp.unversitet.entity.Groups;
import uz.pdp.unversitet.repozitary.FacultyRepository;
import uz.pdp.unversitet.repozitary.GroupsRepository;
import uz.pdp.unversitet.repozitary.TeacherRepository;

import java.util.Optional;

@Service
public class GroupsService {
    private final GroupsRepository groupsRepository;
    private final FacultyRepository facultyRepository;
    private final TeacherRepository teacherRepository;
    @Autowired
    public GroupsService(GroupsRepository groupsRepository, FacultyRepository facultyRepository, TeacherRepository teacherRepository) {
        this.groupsRepository = groupsRepository;
        this.facultyRepository = facultyRepository;
        this.teacherRepository = teacherRepository;
    }
    public ApiResponse addGroups(GroupsDTO groupsDTO){
        if (groupsRepository.existsByName(groupsDTO.getName()))
            return new ApiResponse("Already exist Groups",false);
        if (!teacherRepository.existsByIdIn(groupsDTO.getTeacherListId()))
            return new ApiResponse("Teacher not found" ,false);
        if (!facultyRepository.findById(groupsDTO.getFacultyId()).isPresent())
            return new ApiResponse("faculty not found",false);
        Groups groups = new Groups();
        groups.setName(groupsDTO.getName());
        groups.setFaculty(facultyRepository.findById(groupsDTO.getFacultyId()).get());
        groups.setTeacherList(teacherRepository.findAllByIdIn(groupsDTO.getTeacherListId()));
        groupsRepository.save(groups);
        return new ApiResponse("Successfully Added",true);
    }

    public ApiResponse editGroups(Integer id,GroupsDTO groupsDTO){
        Optional<Groups> optionalGroups = groupsRepository.findById(id);
        if (!optionalGroups.isPresent())
            return new ApiResponse("Not found Groups",false);
        if (groupsRepository.existsByName(groupsDTO.getName()))
            return new ApiResponse("Already exist Groups",false);
        if (!teacherRepository.existsByIdIn(groupsDTO.getTeacherListId()))
            return new ApiResponse("Teacher not found",false);
        if (!facultyRepository.findById(groupsDTO.getFacultyId()).isPresent())
            return new ApiResponse("Faculty not found",false);
        Groups groups = optionalGroups.get();
        groups.setName(groupsDTO.getName());
        groups.setFaculty(facultyRepository.findById(groupsDTO.getFacultyId()).get());
        groups.setTeacherList(teacherRepository.findAllByIdIn(groupsDTO.getTeacherListId()));
        groupsRepository.save(groups);
        return new ApiResponse("Successfully Added",true);
    }

    public ApiResponse deletedGropus(Integer id){
        Optional<Groups> groupsOptional = groupsRepository.findById(id);
        if (groupsOptional.isPresent()){
            groupsRepository.findById(id);
            return new ApiResponse("Successfully deleted",true);
        }else return new ApiResponse("Groups not found",false);
    }
}
