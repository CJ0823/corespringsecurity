package io.security.corespringsecurity.module.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Long id;
    private String username;
    private String email;
    private Integer age;
    private String password;
}
