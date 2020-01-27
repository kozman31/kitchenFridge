package com.thekitchenfridge.dto;

import com.thekitchenfridge.security.entities.Authority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicUserDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
