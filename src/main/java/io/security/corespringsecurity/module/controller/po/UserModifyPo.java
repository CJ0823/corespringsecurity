package io.security.corespringsecurity.module.controller.po;

import io.security.corespringsecurity.module.service.dto.UserBaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserModifyPo extends UserBasePo {

    private Long roleId;
}
