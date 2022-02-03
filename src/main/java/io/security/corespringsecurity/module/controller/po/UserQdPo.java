package io.security.corespringsecurity.module.controller.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserQdPo {

    private Long id;
    private String username;
    private String email;
    private Integer age;
    private String password;
    private String roleName;
}
