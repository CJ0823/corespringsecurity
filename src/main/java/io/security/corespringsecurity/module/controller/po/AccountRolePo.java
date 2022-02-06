package io.security.corespringsecurity.module.controller.po;

import lombok.Data;

@Data
public class AccountRolePo {
    private Long roleId;
    private String roleName;
    private Boolean isChecked;
}
