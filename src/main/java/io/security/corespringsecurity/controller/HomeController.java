package io.security.corespringsecurity.controller;


import io.security.corespringsecurity.domain.Account;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() throws Exception {
		return "home";
	}


	@GetMapping("/denied")
	public String accessDenied(String exception, Model model) {
		//사용자 정보도 전달함
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Account account = (Account) authentication.getPrincipal();
		model.addAttribute("username", account.getUsername());
		model.addAttribute("exception", exception);

		return "user/login/denied";
	}
}
