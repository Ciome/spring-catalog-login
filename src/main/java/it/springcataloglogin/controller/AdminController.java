package it.springcataloglogin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import it.springcataloglogin.entity.User;
import it.springcataloglogin.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/showUserList")
	public String showUserList(Model model) {
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "admin/user-list";
	}
	
	@GetMapping("/enableAdminPrivileges")
	public String enableAdminPrivileges(@ModelAttribute("id") Long id, Model model) {
		userService.makeAdminById(id);
		return "redirect:/admin/showUserList";
	}
	
	@GetMapping("/disableAdminPrivileges")
	public String disableAdminPrivileges(@ModelAttribute("id") Long id, Model model) {
		userService.removeAdminById(id);
		return "redirect:/admin/showUserList";
	}
	
	@GetMapping("/banUser")
	public String banUser(@ModelAttribute("id") Long id, Model model) {
		userService.disableById(id);
		return "redirect:/admin/showUserList";
	}
	
	@GetMapping("/unbanUser")
	public String unbanUser(@ModelAttribute("id") Long id, Model model) {
		userService.enableById(id);
		return "redirect:/admin/showUserList";
	}
	
	@GetMapping("/deleteUser")
	public String deleteUser(@ModelAttribute("id") Long id, Model model) {
		userService.deleteById(id);
		return "redirect:/admin/showUserList";
	}
	
}
