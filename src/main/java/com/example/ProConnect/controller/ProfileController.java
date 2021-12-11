package com.example.ProConnect.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ProConnect.model.Profile;
import com.example.ProConnect.model.User;
import com.example.ProConnect.repository.ProfileRepository;
import com.example.ProConnect.service.ProfileService;
import com.example.ProConnect.service.UserService;


@Controller
public class ProfileController {

	
	@Autowired
	UserService UserService;
	@Autowired
	private ProfileService ProfileService;
	@Autowired
	private ProfileRepository ProfileRepository;
	
	@GetMapping("/user-profile/{email}")
	public String showOtherUserProfilePage(@PathVariable("email") String email,@ModelAttribute("user") User user,Model model,@ModelAttribute("uid") String userEmail)
	{
		ProfileService.showOtherUserProfilePage(email, user, model, userEmail);
		System.out.println("In-user-profile:"+userEmail);
		return "user-profile";
		
	}

	@GetMapping("/my-profile/{email}")
	public String showUserProfilePage(@PathVariable("email") Profile email,@ModelAttribute("user") User user,Model model)
	{
		ProfileService.showUserProfile(email,model);
		return "my-profile";
	}
	
	@PostMapping("/my-profile/{email}")
	public String updateUserProfile(@PathVariable("email") String email,@ModelAttribute("user") User user,@ModelAttribute("profile") Profile profile,RedirectAttributes redirectAttrs) {
		System.out.println("-Updated-");
		ProfileRepository.save(profile);
		redirectAttrs.addAttribute("user", user);
		return "redirect:/user-dashboard";
	}
	
}
