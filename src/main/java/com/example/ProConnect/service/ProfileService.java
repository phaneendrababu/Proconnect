package com.example.ProConnect.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.ProConnect.model.Collaborator;
import com.example.ProConnect.model.Connection;
import com.example.ProConnect.model.Profile;
import com.example.ProConnect.model.Project;
import com.example.ProConnect.model.User;
import com.example.ProConnect.repository.CollaboratorRepository;
import com.example.ProConnect.repository.ConnectionRepository;
import com.example.ProConnect.repository.ProfileRepository;
import com.example.ProConnect.repository.ProjectRepository;

@Service
public class ProfileService 
{
	@Autowired
	private ProfileRepository ProfileRepository;
	@Autowired
	private ProjectRepository ProjectRepository;
	@Autowired
	private ConnectionRepository ConnectionRepository;
	@Autowired
	private CollaboratorRepository CollaboratorRepository;
	@Autowired
	private ProjectService ProjectService;
	
	public void showUserProfile(Profile email,Model model)
	{
		Example<Profile> example=Example.of(email);
		List<Profile> Profile=ProfileRepository.findAll(example);
		model.addAttribute("college", Profile.get(0).getCollege());
		model.addAttribute("place", Profile.get(0).getPlace());
		model.addAttribute("mobile",Profile.get(0).getMobile());
		model.addAttribute("skills",Profile.get(0).getSkills());
	}
	
	public void showOtherUserProfilePage(String email,User user,Model model,String userEmail)
	{
				
		model.addAttribute("userEmail", userEmail);
		
		Project Project=new Project();
		Project.setEmail(email);
		Project.setType("Yes");
		Example<Project> exampleProject=Example.of(Project);
		List<Project> py=ProjectRepository.findAll(exampleProject);
		model.addAttribute("ylist", py);
		
		Profile Profile=new Profile();
		Profile.setEmail(email);
		Example<Profile> exampleProfile=Example.of(Profile);
		List<Profile> profiles=ProfileRepository.findAll(exampleProfile);
		
		
		
		
		Connection connection1=new Connection();
		connection1.setFrom(userEmail);
		connection1.setTo(email);
		
		Example<Connection> econnection1=Example.of(connection1);
		List<Connection> connectionList1=ConnectionRepository.findAll(econnection1);
		
		Connection connection2=new Connection();
		connection2.setFrom(email);
		connection2.setTo(userEmail);
		
		Example<Connection> econnection2=Example.of(connection2);
		List<Connection> connectionList2=ConnectionRepository.findAll(econnection2);
		
		if((connectionList1.size()==1 && connectionList1.get(0).getStatus().equals("1"))
			|| (connectionList2.size()==1 && connectionList2.get(0).getStatus().equals("1")))
		{
			Project p1=new Project();
			p1.setEmail(email);
			p1.setType("No");
			Example<Project> e1=Example.of(p1);
			List<Project> pn=ProjectRepository.findAll(e1);
			//model.addAttribute("nlist", pn);
			
			Collaborator collaborator=new Collaborator();
			collaborator.setFromEmail(userEmail);
			//System.out.println(collaborator.toString());
			Example<Collaborator> ecollaborator=Example.of(collaborator);
			List<Collaborator> collabList=CollaboratorRepository.findAll(ecollaborator);
			
			System.out.println(collabList.size()+"is collab size");
			
			List<List<Project>> projectsList=new ArrayList<List<Project>>();
			List<Project> pendingList=new ArrayList<Project>();
			List<Project> acceptList=new ArrayList<Project>();
			List<Project> rejectList=new ArrayList<Project>();
			List<Project> notSentList=new ArrayList<Project>();
			
			if(collabList.size()>0)
			{
				projectsList=ProjectService.getProjectsList(pn, collabList);
				pendingList=projectsList.get(0);
				acceptList=projectsList.get(1);
				rejectList=projectsList.get(2);
				notSentList=projectsList.get(3);
			}
			else
			{
				notSentList=pn;
			}
			
			
			model.addAttribute("pendingList", pendingList);
			model.addAttribute("acceptList",acceptList);
			model.addAttribute("rejectList", rejectList);
			model.addAttribute("notSentList", notSentList);
			
			model.addAttribute("aprofiles", profiles);
		}
		else if(connectionList1.size()==1 && connectionList1.get(0).getStatus().equals("0"))
		{
			model.addAttribute("message", "Your Connection Request Sent");
			model.addAttribute("pprofiles", profiles);
			
		}
		else if(connectionList1.size()==1 && connectionList1.get(0).getStatus().equals("2"))
		{
			model.addAttribute("message", "Your Connection Request Declined");
			model.addAttribute("rprofiles", profiles);
			
		}
		
		else if(connectionList1.size()==0)
		{
			model.addAttribute("message", "Please Connect to Unlock user details");
			model.addAttribute("profiles",profiles);
		}
	}
	
	public List<Profile> removeProfile(List<Profile> profileList,String userEmail)
	{
		for(int i=0;i<profileList.size();i++) {
			if(userEmail.equals(profileList.get(i).getEmail())) {
				profileList.remove(i);
			}
		}
		return profileList;
	}
	
	public List<Profile> filterProfiles(List<Profile> updatedProfileList,List<User> updatedUserList,String sdata)
	{
		List<Profile> filteredProfiles=new ArrayList<Profile>();
		for(int i=0;i<updatedProfileList.size();i++)
		{
			sdata=sdata.toLowerCase();
			if(updatedProfileList.get(i).getCollege().toLowerCase().contains(sdata)
				||updatedProfileList.get(i).getPlace().toLowerCase().contains(sdata)
				||updatedProfileList.get(i).getSkills().toLowerCase().contains(sdata)
				||updatedUserList.get(i).getName().toLowerCase().contains(sdata))
			{
				filteredProfiles.add(updatedProfileList.get(i));
				//System.out.println(updatedProfileList.get(i).getSkills().toLowerCase());
			}
		}
		return filteredProfiles;
	}
	
}
