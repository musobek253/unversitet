package uz.pdp.unversitet.controller.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.unversitet.dto.ApiResponse;
import uz.pdp.unversitet.dto.GroupsDTO;
import uz.pdp.unversitet.entity.Groups;
import uz.pdp.unversitet.repozitary.GroupsRepository;
import uz.pdp.unversitet.service.GroupsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/groups")
public class GroupsController {
    private final GroupsRepository groupsRepository;
    private final GroupsService groupsService;

    @Autowired
    public GroupsController(GroupsRepository groupsRepository, GroupsService groupsService) {
        this.groupsRepository = groupsRepository;
        this.groupsService = groupsService;
    }

    @GetMapping("/all")
    public List<Groups> getGroupsAll(){
        return groupsRepository.findAll();
    }
    @GetMapping("/{id}")
    public Groups getByIdteacher(@PathVariable Integer id){
        Optional<Groups> optionalGroups = groupsRepository.findById(id);
        return optionalGroups.orElseGet(Groups::new);
    }
    @PostMapping("/add")
    public ApiResponse addGroups(@RequestBody GroupsDTO groupsDTO){
        return groupsService.addGroups(groupsDTO);
    }

    @PutMapping("/{id}")
    public ApiResponse editGroups(@RequestBody GroupsDTO groupsDTO,@PathVariable Integer id){
        return groupsService.editGroups(id, groupsDTO);
    }
    @DeleteMapping("/{id}")
    public ApiResponse deletedByIdGroups(@PathVariable Integer id){
        return groupsService.deletedGropus(id);
    }
}
