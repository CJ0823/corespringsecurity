package io.security.corespringsecurity.module.domain.dto;

import io.security.corespringsecurity.module.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRoleDto {

    private String id;
    private String username;
    private String email;
    private int age;
    private String password;
    private List<Role> roles;
}
