package io.security.corespringsecurity.module.service;

import io.security.corespringsecurity.module.service.dto.AccountDto;
import io.security.corespringsecurity.module.service.dto.UserCreateDto;
import io.security.corespringsecurity.module.service.dto.UserModifyDto;
import io.security.corespringsecurity.module.service.dto.UserQdDto;

import java.util.List;

public interface UserService {

    void createUser(UserCreateDto userCreateDto);

    void modifyUser(UserModifyDto userModifyDto);

    List<UserQdDto> getUsers();

    AccountDto getUser(Long id);

    void deleteUser(Long idx);

    void order();

}
