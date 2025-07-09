package com.jstn9.simplebankapi.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDTO {
    private UUID id;
    private String firstName;
    private String lastName;
}
