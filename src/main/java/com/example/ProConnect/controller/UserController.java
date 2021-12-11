package com.example.ProConnect.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ProConnect.model.User;
import com.example.ProConnect.repository.UserRepository;
import com.example.ProConnect.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserRepository UserRepository;
	@Autowired
	private UserService UserService;
	
	
	@GetMapping("/register")
    public String showRegisterPage(Model model) {
        return "registration";
    }
	
	@GetMapping("/login")
	public String showLoginPage(Model model)
	{
		model.addAttribute("user",new User());
        return "login";
	}
	
	
	@GetMapping("/user-dashboard")
	public String showDashboardPage(Model model,@ModelAttribute User user)
	{
		UserService.showUserDashboardPage(user,model);
		return "user-dashboard";
		
	}
	
	@PostMapping("/register")    
	public String registerUser(@Valid User user,Errors errors,@ModelAttribute("user") User muser)  
	{ 
		if(errors.hasErrors()) {
			return "registration";
		}
		else
		{           
			if(!UserRepository.existsById(muser.getEmail())) {
				UserService.saveAndInitializeUser(muser);
				return "user-data";
			}
			else {
				return "redirect:/register";
			}
		}	
	}
	
	@PostMapping("/login")
	public String loginUser(@ModelAttribute User user,RedirectAttributes redirectAttrs) {
			List<User> isValid=UserService.findByEmailAndPassword(user);
			System.out.println(user+""+isValid.size());
			if (isValid.size()==1){
				System.out.println("hii");
				redirectAttrs.addAttribute("user", user);
				return "redirect:/user-dashboard";
			}
			else{
				return "redirect:/login";
			}
	}
	
	@GetMapping("/search-users")
	public String showSearchUsersPage(@ModelAttribute("uid") String userEmail,Model model,@ModelAttribute("searchdata") String sdata)
	{
		UserService.searchUsers(userEmail, model, sdata);
		return "search-users";
	}
	
	@GetMapping("/notifications")
	public String showNotificationsPage(@ModelAttribute("uid") String userEmail,Model model)
	{
		UserService.showUserNotifications(userEmail, model);
		return "notifications";
	}
	
}
