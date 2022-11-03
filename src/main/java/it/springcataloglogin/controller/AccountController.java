package it.springcataloglogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.springcataloglogin.entity.User;
import it.springcataloglogin.service.UserService;
import it.springcataloglogin.user.CrmUser;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private UserService userService;
	
    @Autowired
    private BCryptPasswordEncoder encoder;

	@GetMapping("/")
	public String showAccountDetails(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByName(auth.getName());

		CrmUser crmUser = new CrmUser();
		crmUser.setUsername(user.getUsername());
		crmUser.setFirstName(user.getUserData().getFirstName());
		crmUser.setLastName(user.getUserData().getLastName());
		crmUser.setEmail(user.getUserData().getEmail());
		model.addAttribute("crmUser", crmUser);
		return "account/account-details";
	}

	@PostMapping("/updateAccountForm")
	public String updateAccountForm(@ModelAttribute("crmUser") CrmUser crmUser, Model model) {
		if (!encoder.matches(crmUser.getOldPassword(), userService.findUserByName(crmUser.getUsername()).getPassword())) {
			model.addAttribute("passwordError", "wrong password");
			System.out.println("fail");
			return "account/account-details";
		}
		System.out.println("win");

		userService.update(crmUser);
		return "home";
	}
}
