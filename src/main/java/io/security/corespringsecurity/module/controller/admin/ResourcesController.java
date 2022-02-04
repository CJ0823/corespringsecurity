package io.security.corespringsecurity.module.controller.admin;

import io.security.corespringsecurity.module.controller.po.RoleResourcesPo;
import io.security.corespringsecurity.module.service.dto.ResourcesDto;
import io.security.corespringsecurity.module.service.dto.RoleResourcesDto;
import io.security.corespringsecurity.module.domain.entity.Resource;
import io.security.corespringsecurity.module.domain.entity.Role;
import io.security.corespringsecurity.module.service.ResourcesService;
import io.security.corespringsecurity.module.service.RoleService;
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
public class ResourcesController {

    private final ResourcesService resourcesService;
    private final RoleService roleService;

    @GetMapping(value="/admin/resources")
    public String getResources(Model model) throws Exception {

        List<Resource> resources = resourcesService.getResources();
        model.addAttribute("resources", resources);

        return "admin/resource/list";
    }

    @GetMapping("/admin/resource/register")
    public String viewCreateResource(Model model) throws Exception {
        List<Role> roles = roleService.getRoles();
        model.addAttribute("resource", new ResourcesDto());
        model.addAttribute("roles", roles);
        return "/admin/resource/register";
    }

    @PostMapping(value="/admin/resource/register")
    public String createResource(RoleResourcesPo roleResourcesPo) throws Exception {

        ModelMapper modelMapper = new ModelMapper();
        RoleResourcesDto roleResourcesDto = modelMapper.map(roleResourcesPo, RoleResourcesDto.class);

        resourcesService.createRoleAndResources(roleResourcesDto);

        return "redirect:/admin/resource/list";
    }

    @GetMapping(value="/admin/resource")
    public String getResource(Model model) throws Exception {

        List<Role> roleList = roleService.getRoles();
        model.addAttribute("roleList", roleList);

        ResourcesDto resources = new ResourcesDto();
        model.addAttribute("resources", resources);

        return "admin/resource/detail";
    }

    @GetMapping(value="/admin/resources/{id}")
    public String getResources(@PathVariable String id, Model model) throws Exception {

        List<Role> roleList = roleService.getRoles();
        model.addAttribute("roleList", roleList);
        Resource resource = resourcesService.getResources(Long.valueOf(id));

        ModelMapper modelMapper = new ModelMapper();
        ResourcesDto resourcesDto = modelMapper.map(resource, ResourcesDto.class);
        model.addAttribute("resources", resourcesDto);

        return "admin/resource/detail";
    }

    @GetMapping(value="/admin/resources/delete/{id}")
    public String removeResources(@PathVariable String id, Model model) throws Exception {

        Resource resource = resourcesService.getResources(Long.valueOf(id));
        resourcesService.deleteResources(Long.valueOf(id));

        return "redirect:/admin/resources";
    }
}