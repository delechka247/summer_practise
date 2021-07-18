package ru.itis.javalab.videomicroservice.dto;

import lombok.Data;

@Data
public class EmailPasswordDto {
    private String email;
    private String password;
}
