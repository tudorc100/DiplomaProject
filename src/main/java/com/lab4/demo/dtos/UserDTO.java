package com.lab4.demo.dtos;
import lombok.*;

import java.util.Set;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {
    private Long id;
    private String email;

    private String name;
    private String cnp;
    private String username;
    private String password;

    private List familyMembers;
    private Set<String> roles;
}
