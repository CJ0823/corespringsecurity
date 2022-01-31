package io.security.corespringsecurity.domain.dto;

import io.security.corespringsecurity.domain.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class RoleResourcesDto {

    private String id;
    private String resourceName;
    private String httpMethod;
    private String resourceType;
    private String roleName;
    private List<Role> roles;

}
