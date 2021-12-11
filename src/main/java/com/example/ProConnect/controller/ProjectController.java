package com.example.ProConnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ProConnect.model.Project;
import com.example.ProConnect.model.User;
import com.example.ProConnect.repository.ProjectRepository;

@Controller
public class ProjectController {

	@Autowired
	private ProjectRepository ProjectRepository;

	
	@GetMapping("/add-project/{email}")
	public String showAddProjectPage(@ModelAttribute("user") User user,Model model) {
		return "add-project";
	}
	

	@PostMapping("/add-project/{email}")
	public String addProject(@ModelAttribute("user") User user,@ModelAttribute("project") Project project,RedirectAttributes redirectAttrs) {
		project.setId();
		ProjectRepository.save(project);
		redirectAttrs.addAttribute("user",user);
		return "redirect:/user-dashboard";
	}
	
	
}
