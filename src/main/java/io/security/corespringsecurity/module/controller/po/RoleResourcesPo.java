package io.security.corespringsecurity.module.controller.po;

import lombok.Data;

@Data
public class RoleResourcesPo {

    private String id;
    private String resourceName;
    private String httpMethod;
    private String resourceType;
    private String roleName;

}
