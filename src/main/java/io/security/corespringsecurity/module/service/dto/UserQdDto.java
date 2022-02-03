package io.security.corespringsecurity.module.service.dto;

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
public class UserQdDto {

    private Long id;
    private String username;
    private String email;
    private Integer age;
    private String password;
    private String roleName;
}
