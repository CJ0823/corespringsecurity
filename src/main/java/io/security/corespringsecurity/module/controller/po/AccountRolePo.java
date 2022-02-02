package io.security.corespringsecurity.module.controller.po;

import io.security.corespringsecurity.module.domain.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class AccountRolePo {
    private String id;
    private String username;
    private String email;
    private int age;
    private String password;
    private List<Role> roles;
}
