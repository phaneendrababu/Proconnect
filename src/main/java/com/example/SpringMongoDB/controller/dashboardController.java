package com.example.SpringMongoDB.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.SpringMongoDB.repository.UserRepositoryCrud;

@Controller
public class dashboardController {
	@Autowired
	private UserRepositoryCrud repository;
	
	/*@GetMapping("/user-dashboard")
	public <User> ModelAndView userDashboardPage(ModelAndView mav)
	{
		List<User> userList=(List<User>) repository.findAll();
		mav.addObject("userList",userList);
        return mav;
	}*/
}
