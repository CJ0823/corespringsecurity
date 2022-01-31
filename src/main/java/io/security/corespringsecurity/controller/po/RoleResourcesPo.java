package io.security.corespringsecurity.controller.po;

import io.security.corespringsecurity.domain.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class RoleResourcesPo {

    private String id;
    private String resourceName;
    private String httpMethod;
    private String resourceType;
    private String roleName;
    private List<Role> roles;

}
