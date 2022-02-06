package io.security.corespringsecurity.module.service.dto;

import lombok.Data;

@Data
public class RoleResourcesDto {

    private String id;
    private String resourceName;
    private String httpMethod;
    private String resourceType;
    private String roleName;

}
