package io.security.corespringsecurity.module.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserBaseDto {

    private Long id;
    private String username;
    private String email;
    private Integer age;

}
