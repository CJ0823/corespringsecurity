package io.security.corespringsecurity.module.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConfigController {

    @GetMapping("/config")
    public String home() throws Exception {
        return "config";
    }
}