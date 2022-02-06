package io.security.corespringsecurity.module.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserModifyDto extends UserBaseDto {

    private List<AccountRoleDto> accountRoles;
}
