package io.security.corespringsecurity.module.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserBaseDto {

    private String username;
    private String email;
    private Integer age;
    private String password;

}
