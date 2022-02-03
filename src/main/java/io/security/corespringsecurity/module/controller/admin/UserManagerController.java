package io.security.corespringsecurity.module.controller.admin;

import io.security.corespringsecurity.module.controller.po.UserModifyPo;
import io.security.corespringsecurity.module.controller.po.UserQdPo;
import io.security.corespringsecurity.module.service.dto.AccountDto;
import io.security.corespringsecurity.module.service.dto.UserModifyDto;
import io.security.corespringsecurity.module.service.dto.UserQdDto;
import io.security.corespringsecurity.module.domain.entity.Role;
import io.security.corespringsecurity.module.service.RoleService;
import io.security.corespringsecurity.module.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserManagerController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping(value="/admin/accounts")
    public String getUsers(Model model) throws Exception {

        List<UserQdDto> accounts = userService.getUsers();

        model.addAttribute("accounts", accounts);

        return "admin/user/list";
    }

    @PostMapping(value="/admin/accounts")
    public String modifyUser(UserModifyPo userModifyPo) throws Exception {

        ModelMapper mapper = new ModelMapper();
        UserModifyDto userModifyDto = new UserModifyDto();
        mapper.map(userModifyPo, userModifyDto);
        userService.modifyUser(userModifyDto);

        return "redirect:/admin/accounts";
    }

    @GetMapping(value = "/admin/accounts/{id}")
    public String getUser(@PathVariable(value = "id") Long id, Model model) {

        AccountDto accountDto = userService.getUser(id);
        List<Role> roleList = roleService.getRoles();

        model.addAttribute("account", accountDto);
        model.addAttribute("roleList", roleList);

        return "admin/user/detail";
    }

    @GetMapping(value = "/admin/accounts/delete/{id}")
    public String removeUser(@PathVariable(value = "id") Long id, Model model) {

        userService.deleteUser(id);

        return "redirect:/admin/users";
    }
}
