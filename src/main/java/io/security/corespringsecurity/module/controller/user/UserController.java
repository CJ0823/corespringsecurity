package io.security.corespringsecurity.module.controller.user;


import io.security.corespringsecurity.module.controller.po.UserCreatePo;
import io.security.corespringsecurity.module.domain.entity.Role;
import io.security.corespringsecurity.module.service.RoleService;
import io.security.corespringsecurity.module.service.UserService;
import io.security.corespringsecurity.module.service.dto.UserCreateDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;


    @GetMapping("/mypage")
    public String myPage() {
        userService.order();
        return "/mypage";
    }

    @GetMapping("/users")
    public String createUser(Model model) {
        List<Role> roles = roleService.getRoles();
        model.addAttribute("roles", roles);
        return "user/login/register";
    }

    @PostMapping("/users")
    public String createUser(UserCreatePo userCreatePo) {

        ModelMapper mapper = new ModelMapper();
        UserCreateDto userCreateDto = new UserCreateDto();
        mapper.map(userCreatePo, userCreateDto);

        userService.createUser(userCreateDto);

        return "redirect:/";
    }

}
