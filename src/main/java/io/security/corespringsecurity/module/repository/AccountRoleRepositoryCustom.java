package io.security.corespringsecurity.module.repository;

import io.security.corespringsecurity.module.service.dto.UserQdDto;

import java.util.List;

public interface AccountRoleRepositoryCustom {

    List<UserQdDto> getUsers();
}
