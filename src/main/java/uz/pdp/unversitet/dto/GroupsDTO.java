package uz.pdp.unversitet.dto;

import lombok.Data;

import java.util.List;

@Data
public class GroupsDTO {
    private String name;
    private Integer facultyId;
    private List<Integer> teacherListId;
}
