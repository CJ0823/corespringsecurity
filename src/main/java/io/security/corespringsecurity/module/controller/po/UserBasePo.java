package io.security.corespringsecurity.module.controller.po;

import lombok.Data;

@Data
public class UserBasePo {

    private Long id;
    private String username;
    private String email;
    private Integer age;
}
