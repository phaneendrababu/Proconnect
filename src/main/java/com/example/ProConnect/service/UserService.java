package com.example.ProConnect.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.ProConnect.model.Collaborator;
import com.example.ProConnect.model.Connection;
import com.example.ProConnect.model.Posts;
import com.example.ProConnect.model.Profile;
import com.example.ProConnect.model.Project;
import com.example.ProConnect.model.User;
import com.example.ProConnect.repository.CollaboratorRepository;
import com.example.ProConnect.repository.PostsRepository;
import com.example.ProConnect.repository.ProfileRepository;
import com.example.ProConnect.repository.ProjectRepository;
import com.example.ProConnect.repository.UserRepository;

@Service
public class UserService 
{

	@Autowired
	private ProfileRepository ProfileRepository;
	@Autowired
	private UserRepository UserRepository;
	@Autowired
	private PostsRepository PostsRepository;
	@Autowired
	private ProjectRepository ProjectRepository;
	@Autowired
	private CollaboratorRepository CollaboratorRepository;
	
	@Autowired
	private CollaboratorService CollaboratorService;
	@Autowired
	private ProjectService ProjectService;
	@Autowired
	private ProfileService ProfileService;
	
	public void saveAndInitializeUser(User muser)
	{
		
		UserRepository.save(muser);
		
		Profile Profile=new Profile();
		Profile.setEmail(muser.getEmail());
		Profile.setCollege("");
		Profile.setPlace("");
		Profile.setMobile("");
		Profile.setSkills("");
		
		ProfileRepository.save(Profile);
		
	}
	
	public List<User> findByEmailAndPassword(User user)
	{
		Example<User> example=Example.of(user);
		List<User> userList=UserRepository.findAll(example);
		System.out.println(userList.size());
		return UserRepository.findAll(example);
	}
	
	public List<User> removeUser(List<User> userList,User user)
	{
		for(int i=0;i<userList.size();i++) {
			if(userList.get(i).getEmail().equals(user.getEmail())) {
				userList.remove(i);
			}
		}
		return userList;
	}
	
	public List<User> removeUser(List<User> userList,String userEmail)
	{
		for(int i=0;i<userList.size();i++) {
			if(userList.get(i).getEmail().equals(userEmail)) {
				userList.remove(i);
			}
		}
		return userList;
	}
	
	public void showUserDashboardPage(User user,Model model)
	{
		List<Posts> postsList=PostsRepository.findAll(Sort.by(Sort.Direction.DESC, "time"));
		
		List<User> userList=UserRepository.findAll();
		userList=removeUser(userList,user);
		model.addAttribute("user",user);
		model.addAttribute("userEmail", user.getEmail());
		model.addAttribute("userList", userList);
		model.addAttribute("postsList", postsList);
	}
	
	public void searchUsers(String userEmail,Model model,String sdata)
	{
		List<Profile> profileList=ProfileRepository.findAll();
		List<Profile> updatedProfileList=ProfileService.removeProfile(profileList, userEmail);
		List<User> userList=UserRepository.findAll();
		
		List<User> updatedUserList=removeUser(userList, userEmail);
		
		
			
		if(sdata.contentEquals(""))
		{
			model.addAttribute("profiles", updatedProfileList);
			model.addAttribute("users", updatedUserList);
			model.addAttribute("userEmail", userEmail);
			//return "search-users";
		}
		else
		{
			List<Profile> filteredProfiles=ProfileService.filterProfiles(updatedProfileList,updatedUserList,sdata);
			List<User> filteredUsers=filterUsers(filteredProfiles,updatedUserList);
			model.addAttribute("filteredProfiles", filteredProfiles);
			model.addAttribute("filteredUsers", filteredUsers);
			model.addAttribute("userEmail", userEmail);
			//System.out.println("else block");
		}
	}
	
	public void showUserNotifications(String userEmail,Model model)
	{
		Project project1=new Project();
		project1.setEmail(userEmail);
		project1.setType("No");

		Example<Project> eproject=Example.of(project1);
		List<Project> userProjectList=ProjectRepository.findAll(eproject);
		List<Collaborator> collabList=CollaboratorRepository.findAll();
		//List<User> userList=userRepository.findAll();
		
		List<Collaborator> reqList=CollaboratorService.getCollaboratorRequestsList(userProjectList,collabList);
		List<Project> reqProjectsList=ProjectService.getProjectsRequestsList(userProjectList,collabList);
		//List<User> reqUserList=getUserRequestsList(userList,collabList);
		
		Collaborator collaborator1=new Collaborator();
		collaborator1.setFromEmail(userEmail);
		collaborator1.setStatus("1");
		Example<Collaborator> acollaborator=Example.of(collaborator1);
		List<Collaborator> acceptedList=CollaboratorRepository.findAll(acollaborator);
		
		Collaborator collaborator2=new Collaborator();
		collaborator2.setFromEmail(userEmail);
		collaborator2.setStatus("2");
		Example<Collaborator> rcollaborator=Example.of(collaborator2);
		List<Collaborator> rejectedList=CollaboratorRepository.findAll(rcollaborator);
		
		
		Project project2=new Project();
		project2.setType("No");

		Example<Project> eproject2=Example.of(project2);
		List<Project> userProjectList2=ProjectRepository.findAll(eproject2);
		
		
		List<Project> accProjectsList=ProjectService.getProjectsAcceptsList(userProjectList2,acceptedList);
		List<Project> rejProjectsList=ProjectService.getProjectsRejectsList(userProjectList2,rejectedList);
		
		
		model.addAttribute("reqList", reqList);
		model.addAttribute("reqProjectsList", reqProjectsList);
		model.addAttribute("acceptedList", acceptedList);
		model.addAttribute("rejectedList", rejectedList);
		model.addAttribute("accProjectsList", accProjectsList);
		model.addAttribute("rejProjectsList", rejProjectsList);
		model.addAttribute("userEmail",userEmail);
		
	}
	
	public List<User> filterUsers(List<Profile> filteredProfiles,List<User> updatedUserList)
	{
		List<User> filteredUsers=new ArrayList<User>();
		for(int i=0;i<filteredProfiles.size();i++)
		{
			for(int j=0;j<updatedUserList.size();j++)
			{
				if(filteredProfiles.get(i).getEmail().equals(updatedUserList.get(j).getEmail()))
				{
					filteredUsers.add(updatedUserList.get(j));
					break;
				}
			}
		}
		return filteredUsers;
	}
	
	public List<User> getUsersRequestsList(List<Connection> connectionList,List<User> usersList)
	{
		List<User> reqUsersList=new ArrayList<User>();
		for(int i=0;i<connectionList.size();i++)
		{
			for(int j=0;j<usersList.size();j++)
			{
				if(connectionList.get(i).getFrom().equals(usersList.get(j).getEmail()))
				{
					reqUsersList.add(usersList.get(j));
					break;
				}
			}
		}
		return reqUsersList;
	}
	
}
