package uz.pdp.unversitet.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private String FirstName;
    private String lastName;
    private String PhoneNumber;
    private Integer groupsId;
}
