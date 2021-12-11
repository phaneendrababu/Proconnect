package com.example.ProConnect.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ProConnect.model.Collaborator;
import com.example.ProConnect.model.Project;
import com.example.ProConnect.repository.CollaboratorRepository;
import com.example.ProConnect.repository.ProjectRepository;

@Service
public class CollaboratorService {
	
	@Autowired
	private CollaboratorRepository CollaboratorRepository;
	@Autowired
	private ProjectRepository ProjectRepository;
	@Autowired
	private ProjectService ProjectService;
	
	public void saveCollaboratorRequest(Collaborator collaborator,String uid,RedirectAttributes redirectAttrs)
	{
		collaborator.setRid();
		collaborator.setTime();
		CollaboratorRepository.save(collaborator);
		redirectAttrs.addAttribute("uid", uid);
	}
	
	public void viewCollaboratorRequestsPage(String userEmail,Model model)
	{	
		Project project=new Project();
		project.setEmail(userEmail);
		project.setType("No");

		Example<Project> eproject=Example.of(project);
		List<Project> userProjectList=ProjectRepository.findAll(eproject);
		List<Collaborator> collabList=CollaboratorRepository.findAll();
		//List<User> userList=userRepository.findAll();
		
		List<Collaborator> reqList=getCollaboratorRequestsList(userProjectList,collabList);
		List<Project> reqProjectsList=ProjectService.getProjectsRequestsList(userProjectList,collabList);
		//List<User> reqUserList=getUserRequestsList(userList,collabList);
		
		model.addAttribute("reqList", reqList);
		model.addAttribute("reqProjectsList", reqProjectsList);
		model.addAttribute("userEmail",userEmail);
	}
	
	
	public List<Collaborator> getCollaboratorRequestsList(List<Project> userProjectsList,List<Collaborator> collabList)
	{
		List<Collaborator> reqList=new ArrayList<Collaborator>();
		int userProjectsListLen=userProjectsList.size();
		int collabListLen=collabList.size();
		for(int i=0;i<userProjectsListLen;i++)
		{
			for(int j=0;j<collabListLen;j++)
			{
				if(userProjectsList.get(i).getId().equals(collabList.get(j).getPid()) && collabList.get(j).getStatus().equals("0"))
				{
					/*if(!reqList.contains(collabList.get(j)))
					{
						reqList.add(collabList.get(j));
					}*/
					reqList.add(collabList.get(j));
				}
			}
		}
		return reqList;
	}
	
}
