package com.example.ProConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ProConnect.model.Connection;
import com.example.ProConnect.model.User;
import com.example.ProConnect.repository.ConnectionRepository;
import com.example.ProConnect.repository.UserRepository;

@Service
public class ConnectionService {
	
	@Autowired
	private UserRepository UserRepository;
	@Autowired
	private ConnectionRepository ConnectionRepository;
	@Autowired
	private UserService UserService;
	
	
	public void saveConnectionRequest(String from,String to,Model model,RedirectAttributes redirectAttrs)
	{

		Connection connection=new Connection(from,to,"0");
		connection.setRid();
		connection.setTime();
		
		ConnectionRepository.save(connection);
		
		model.addAttribute("userEmail", from);
		redirectAttrs.addAttribute("uid", from);
	}
	
	public void showViewConnectionRequestPage(String userEmail,Model model)
	{
		
		Connection connection=new Connection();
		connection.setTo(userEmail);
		connection.setStatus("0");
		
		Example<Connection> econnection=Example.of(connection);
		List<Connection> connectionList=ConnectionRepository.findAll(econnection);
		List<User> usersList=UserRepository.findAll();
		
		List<User> reqUsersList=UserService.getUsersRequestsList(connectionList,usersList);
		
		
		model.addAttribute("connectionList", connectionList);
		model.addAttribute("reqUsersList", reqUsersList);
		model.addAttribute("userEmail",userEmail);
	}

	public void acceptConnectionRequest(String userEmail,String fromEmail,Model model,RedirectAttributes redirectAttrs)
	{	
		
		Connection connection=new Connection();
		
		connection.setFrom(fromEmail);
		connection.setTo(userEmail);
		
		Example<Connection> econnection=Example.of(connection);
		List<Connection> connectionList=ConnectionRepository.findAll(econnection);
		connection.setRid(connectionList.get(0).getRid());
		connection.setFrom(connectionList.get(0).getFrom());
		connection.setTo(connectionList.get(0).getTo());
		connection.setTime();
		connection.setStatus("1");
		
		ConnectionRepository.save(connection);
		
		redirectAttrs.addAttribute("uid", userEmail);

	}
	
	public void rejectConnectionRequest(String userEmail,String fromEmail,Model model,RedirectAttributes redirectAttrs)
	{	
		
		Connection connection=new Connection();
		
		connection.setFrom(fromEmail);
		connection.setTo(userEmail);
		
		Example<Connection> econnection=Example.of(connection);
		List<Connection> connectionList=ConnectionRepository.findAll(econnection);
		connection.setRid(connectionList.get(0).getRid());
		connection.setFrom(connectionList.get(0).getFrom());
		connection.setTo(connectionList.get(0).getTo());
		connection.setTime();
		connection.setStatus("2");
		
		ConnectionRepository.save(connection);
		
		redirectAttrs.addAttribute("uid", userEmail);

	}
}
