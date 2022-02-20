package uz.pdp.unversitet.dto;

import lombok.Data;

@Data
public class ApiResponse {

    private String message;

    private boolean isCompleted;

    public ApiResponse(String message, boolean isCompleted) {

        this.message = message;

        this.isCompleted = isCompleted;
    }
}
