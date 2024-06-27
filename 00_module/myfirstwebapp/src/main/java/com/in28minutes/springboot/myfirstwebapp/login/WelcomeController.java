package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String goToWelcomePage(ModelMap models) {
		models.addAttribute("name", getLoggedinUserName());
		return "welcome";
	}

	private String getLoggedinUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}