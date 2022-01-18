package io.security.corespringsecurity.controller;


import io.security.corespringsecurity.domain.Account;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() throws Exception {
		return "home";
	}

	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,
						@RequestParam(value = "exception", required = false) String exception, Model model) throws Exception {

		model.addAttribute("error", error);
		model.addAttribute("exception", exception);

		return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication); //로그아웃 필터를 통해서 처리함
		}
		return "redirect:/login";
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
