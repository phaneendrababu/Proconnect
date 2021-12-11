package com.example.ProConnect.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProConnect.model.MessageModel;
import com.example.ProConnect.model.User;
import com.example.ProConnect.repository.UserRepository;

@RestController
@CrossOrigin
public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private UserRepository userRepository;

    
    /*@GetMapping("/chat-main")
    public String showChatMainPage(@ModelAttribute("uid") String userEmail,Model model)
    {
    	model.addAttribute("userEmail",userEmail);
    	return "chat-main";
    }*/
    
    public List<User> removeUser(List<User> userList,String userEmail)
    {
    	for(int i=0;i<userList.size();i++) {
    		if(userList.get(i).getEmail().equals(userEmail)) {
    			userList.remove(i);
    			break;
    		}
    	}
    	return userList;
    }
    
    @GetMapping("/fetchAllUsers/{userEmail}")
    public List<User> fetchAll(Model model,@PathVariable("userEmail") String userEmail) {
    	List<User> userList=userRepository.findAll();
    	List<User> updatedList=removeUser(userList,userEmail);
    	
    	return updatedList;
    }
    
    public User getUser(List<User> userList,String par)
    {
    	for(int i=0;i<userList.size();i++)
    	{
    		if(par.contains(userList.get(i).getEmail()))
    		{
    			return userList.get(i);
    		}
    	}
    	return userList.get(0);
    }
    
    @GetMapping("/findEmail1/{par}")
    public User findEmail(@PathVariable("par") String par)
    {
    	//System.out.println(userEmail);
    	List<User> userList=userRepository.findAll();
    	User user=getUser(userList,par);
    	return user;
    }
    
    
    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, MessageModel message) {
        System.out.println("handling send message: " + message + " to: " + to);
        
        User user=new User();
        user.setName(to);
        Example<User> euser=Example.of(user);
        List<User> userList=userRepository.findAll(euser);
        //boolean isExists = UserStorage.getInstance().getUsers().contains(to);
        if (userList.size()>0) {
            simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
        }
    }
}
