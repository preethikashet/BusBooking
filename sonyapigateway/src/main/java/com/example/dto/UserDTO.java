package com.example.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String teamName;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}