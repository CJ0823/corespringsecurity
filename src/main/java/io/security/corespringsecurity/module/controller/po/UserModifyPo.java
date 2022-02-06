package io.security.corespringsecurity.module.controller.po;

import lombok.Data;

import java.util.List;

@Data
public class UserModifyPo extends UserBasePo {

    private List<AccountRolePo> accountRoles;
}
