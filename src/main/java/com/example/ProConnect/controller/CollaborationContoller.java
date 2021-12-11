package com.example.ProConnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ProConnect.model.Collaborator;
import com.example.ProConnect.service.CollaboratorService;
import com.example.ProConnect.service.ProjectService;

@Controller
public class CollaborationContoller {

	@Autowired
	private CollaboratorService CollaboratorService;
	@Autowired
	private ProjectService ProjectService;
	
	@PostMapping("/user-profile/collaborate/{projectId}")
	public String  saveCollaborateRequest(@PathVariable("projectId") String projectId,@ModelAttribute("collaborator") Collaborator collaborator,
			@ModelAttribute("profileid") String profileid,@ModelAttribute("uid") String uid,RedirectAttributes redirectAttrs)
	{
		CollaboratorService.saveCollaboratorRequest(collaborator,uid,redirectAttrs);
		return "redirect:/user-profile/"+profileid;
	}
	
	@GetMapping("/view-requests")
	public String showViewCollaborationRequestsPage(@ModelAttribute("uid") String userEmail,Model model)
	{
		CollaboratorService.viewCollaboratorRequestsPage(userEmail, model);
		return "view-requests";
	}
	
	@GetMapping("/accept-request")
	public String showAcceptCollaborationRequestpage(@ModelAttribute("uid") String userEmail,
			@ModelAttribute("fromEmail") String fromEmail,@ModelAttribute("pid") String pid,
			Model model,RedirectAttributes redirectAttrs)
	{
		ProjectService.acceptProjectCollaborationRequest(userEmail, fromEmail, pid, model, redirectAttrs);
		return "redirect:/view-requests";
		
	}
	
	@GetMapping("/reject-request")
	public String showRejectCollaborationRequestpage(@ModelAttribute("uid") String userEmail,
			@ModelAttribute("fromEmail") String fromEmail,@ModelAttribute("pid") String pid,
			Model model,RedirectAttributes redirectAttrs)
	{
		ProjectService.rejectProjectCollaborationRequest(userEmail, fromEmail, pid, model, redirectAttrs);
		return "redirect:/view-requests";
		
	}
	
}
