package com.example.ProConnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ProConnect.model.Posts;
import com.example.ProConnect.model.User;
import com.example.ProConnect.service.PostService;

@Controller
public class PostController {

	@Autowired
	private PostService PostService;
	
	
	@GetMapping("/create-post/{email}")
	public String showCreatePostPage(@ModelAttribute("user") User user,Model model)
	{
		PostService.showCreatePostPage(user, model);
		return "create-post";
	}
	@GetMapping("/my-posts")
	public String showMyPostsPage(@ModelAttribute("uid") String userEmail,Model model)
	{
		PostService.showMyPosts(userEmail, model);
		return "my-posts";
	}
	
	@GetMapping("/delete-post/{email}/{id}")
	public String deletePost(@PathVariable("email") String userEmail,@PathVariable("id") String id,Model model,RedirectAttributes redirectAttrs)
	{
		PostService.deletePost(userEmail, id, model, redirectAttrs);
		return "redirect:/my-posts";
	}
	
	@GetMapping("/comment/{email}/{id}")
	public String showCommentPage(@ModelAttribute("email") String email,@PathVariable(name = "id")  String id,Model model)
	{
		PostService.showCommentPage(id,model);
		return "post-comment";
	}
	
	
	@PostMapping("/create-post/{email}")
	public String createPost(@ModelAttribute("user") User user,@ModelAttribute("posts") Posts posts,RedirectAttributes redirectAttrs) 
	{
		PostService.createPost(posts);
		redirectAttrs.addAttribute("user", user);
		return "redirect:/user-dashboard";
	}
	
	
	@PostMapping("/comment/{email}/{id}")
	public String addComment(@PathVariable("id") String id,@ModelAttribute("email") String email,@ModelAttribute("post_comment") Posts post, RedirectAttributes redirectAttrs) 
	{
		PostService.addComment(id,email,post,redirectAttrs);
		return "redirect:/user-dashboard";
	}
}
