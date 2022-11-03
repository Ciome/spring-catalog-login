package it.springcataloglogin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.springcataloglogin.entity.User;
import it.springcataloglogin.service.UserService;
import it.springcataloglogin.user.CrmUser;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	private UserService userService;

	@GetMapping("/showRegistrationForm")
	public String showRegistrationForm(Model model) {
		model.addAttribute("crmUser", new CrmUser());
		return "registration";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(@Valid @ModelAttribute("crmUser") CrmUser crmUser,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors())
			return "registration";

		User existing = userService.findUserByName(crmUser.getUsername());

		if (existing != null) {
			model.addAttribute("crmUser", crmUser);
			model.addAttribute("registrationError", "User name already exists.");

			return "registration";
		}
		userService.save(crmUser);

		return "registration-success";
	}
}
