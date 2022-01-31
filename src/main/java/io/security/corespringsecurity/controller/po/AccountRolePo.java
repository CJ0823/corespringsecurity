package io.security.corespringsecurity.controller.po;

import io.security.corespringsecurity.domain.entity.Role;
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
