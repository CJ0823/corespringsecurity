package io.security.corespringsecurity.module.controller.admin;

import io.security.corespringsecurity.module.domain.entity.Role;
import io.security.corespringsecurity.module.service.RoleResourceService;
import io.security.corespringsecurity.module.service.RoleService;
import io.security.corespringsecurity.module.service.dto.RoleDto;
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
public class RoleController {

    private final RoleService roleService;
    private final RoleResourceService roleResourceService;

    @GetMapping(value="/admin/roles")
    public String getRoles(Model model) throws Exception {

        List<Role> roles = roleService.getRoles();
        model.addAttribute("roles", roles);

        return "admin/role/list";
    }

    @GetMapping(value="/admin/roles/register")
    public String viewCreateRole(Model model) throws Exception {
        model.addAttribute("role", new RoleDto());
        return "admin/role/register";
    }

    @PostMapping(value="/admin/roles/register")
    public String createRole(RoleDto roleDto) throws Exception {

        ModelMapper modelMapper = new ModelMapper();
        Role role = modelMapper.map(roleDto, Role.class);
        roleService.createRole(role);

        return "redirect:/admin/roles";
    }

    @GetMapping(value="/admin/roles/{id}")
    public String getRole(@PathVariable String id, Model model) throws Exception {

        Role role = roleService.getRole(Long.parseLong(id));

        ModelMapper modelMapper = new ModelMapper();
        RoleDto roleDto = modelMapper.map(role, RoleDto.class);
        model.addAttribute("role", roleDto);

        return "admin/role/detail";
    }

    @PostMapping(value="/admin/roles/delete/{id}")
    public String removeRoles(@PathVariable String id, Model model) throws Exception {

        roleResourceService.deleteRoleSourcesByRoleId(Long.parseLong(id));
        roleService.deleteRole(Long.parseLong(id));

        List<Role> roles = roleService.getRoles();
        model.addAttribute("roles", roles);

        return "redirect:/admin/roles";
    }

}
