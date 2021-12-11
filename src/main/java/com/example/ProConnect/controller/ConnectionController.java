package com.example.ProConnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ProConnect.service.ConnectionService;

@Controller
public class ConnectionController {

	@Autowired
	private ConnectionService ConnectionService;

	@GetMapping("/send-connect-request")
	public String saveConnectionRequestPage(@ModelAttribute("from") String from,@ModelAttribute("to") String to,Model model,RedirectAttributes redirectAttrs)
	{
		ConnectionService.saveConnectionRequest(from, to, model, redirectAttrs);
		return "redirect:/user-profile/"+to;
	}
	
	@GetMapping("/view-connection-requests")
	public String showViewConnectionRequestsPage(@ModelAttribute("uid") String userEmail,Model model)
	{
		ConnectionService.showViewConnectionRequestPage(userEmail, model);
		return "view-connection-requests";
	}
	
	@GetMapping("/accept-connection-request")
	public String showAcceptConnectionRequestpage(@ModelAttribute("uid") String userEmail,
			@ModelAttribute("fromEmail") String fromEmail,
			Model model,RedirectAttributes redirectAttrs)
	{
		ConnectionService.acceptConnectionRequest(userEmail, fromEmail, model, redirectAttrs);
		return "redirect:/view-connection-requests";
	}
	
	@GetMapping("/reject-connection-request")
	public String showRejectConnectionRequestpage(@ModelAttribute("uid") String userEmail,
			@ModelAttribute("fromEmail") String fromEmail,
			Model model,RedirectAttributes redirectAttrs)
	{
		
		ConnectionService.rejectConnectionRequest(userEmail, fromEmail, model, redirectAttrs);
		return "redirect:/view-connection-requests";
	}
	
}

