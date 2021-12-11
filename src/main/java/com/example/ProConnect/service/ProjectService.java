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

@Service
public class ProjectService {
	
	@Autowired
	private CollaboratorRepository CollaboratorRepository;
	
	public void acceptProjectCollaborationRequest(String userEmail,String fromEmail,String pid,Model model,RedirectAttributes redirectAttrs)
	{
		Collaborator collaborator=new Collaborator();
		collaborator.setPid(pid);
		collaborator.setFromEmail(fromEmail);
		
		Example<Collaborator> ecollaborator=Example.of(collaborator);
		List<Collaborator> collabList=CollaboratorRepository.findAll(ecollaborator);
		collaborator.setRid(collabList.get(0).getRid());
		collaborator.setFromEmail(collabList.get(0).getFromEmail());
		collaborator.setPid(collabList.get(0).getPid());
		collaborator.setTime();
		collaborator.setStatus("1");
		
		CollaboratorRepository.save(collaborator);
		
		redirectAttrs.addAttribute("uid", userEmail);
	}
	
	public void rejectProjectCollaborationRequest(String userEmail,String fromEmail,String pid,Model model,RedirectAttributes redirectAttrs)
	{
		Collaborator collaborator=new Collaborator();
		collaborator.setPid(pid);
		collaborator.setFromEmail(fromEmail);
		
		Example<Collaborator> ecollaborator=Example.of(collaborator);
		List<Collaborator> collabList=CollaboratorRepository.findAll(ecollaborator);
		collaborator.setRid(collabList.get(0).getRid());
		collaborator.setFromEmail(collabList.get(0).getFromEmail());
		collaborator.setPid(collabList.get(0).getPid());
		collaborator.setTime();
		collaborator.setStatus("2");
		
		CollaboratorRepository.save(collaborator);
		
		redirectAttrs.addAttribute("uid", userEmail);
	}
	
	public List<List<Project>> getProjectsList(List<Project> pn,List<Collaborator> collabList){
		
		List<List<Project>> projectsList= new ArrayList<List<Project>>();
		
		List<Project> pendingList=new ArrayList<Project>();
		List<Project> acceptList=new ArrayList<Project>();
		List<Project> rejectList=new ArrayList<Project>();
		List<Project> notSentList=new ArrayList<Project>();
		
		int collabListLen=collabList.size();
		int pnLen=pn.size();
		for(int i=0;i<pnLen;i++)
		{
			int counter=0;
			for(int j=0;j<collabListLen;j++) 
			{
				if(collabList.get(j).getPid().equals(pn.get(i).getId())) 
				{
					//System.out.println(collabList.get(j));
					if(collabList.get(j).getStatus().equals("0"))
					{
						pendingList.add(pn.get(i));
					}
					else if(collabList.get(j).getStatus().equals("1"))
					{
						
						acceptList.add(pn.get(i));
					}
					else if(collabList.get(j).getStatus().equals("2"))
					{
						
						
						rejectList.add(pn.get(i));
					}
					counter++;
				}
			}
			if(counter==0)
			{
				notSentList.add(pn.get(i));
			}
		}
		
		
		
		
		projectsList.add(pendingList);
		projectsList.add(acceptList);
		projectsList.add(rejectList);
		projectsList.add(notSentList);
		return projectsList;
	}

	public List<Project> getProjectsRequestsList(List<Project> userProjectsList,List<Collaborator> collabList)
	{
		List<Project> reqProjectsList=new ArrayList<Project>();
		int userProjectsListLen=userProjectsList.size();
		int collabListLen=collabList.size();
		for(int i=0;i<userProjectsListLen;i++)
		{
			for(int j=0;j<collabListLen;j++)
			{
				if(userProjectsList.get(i).getId().equals(collabList.get(j).getPid()) && collabList.get(j).getStatus().equals("0"))
				{
					/*if(!reqProjectsList.contains(userProjectsList.get(i)))
					{
						reqProjectsList.add(userProjectsList.get(i));
					}*/
					reqProjectsList.add(userProjectsList.get(i));
				}
			}
		}
		return reqProjectsList;
	}
	
	public List<Project> getProjectsAcceptsList(List<Project> userProjectsList,List<Collaborator> collabList)
	{
		List<Project> reqProjectsList=new ArrayList<Project>();
		int userProjectsListLen=userProjectsList.size();
		int collabListLen=collabList.size();
		for(int i=0;i<userProjectsListLen;i++)
		{
			for(int j=0;j<collabListLen;j++)
			{
				if(userProjectsList.get(i).getId().equals(collabList.get(j).getPid()))
				{
					/*if(!reqProjectsList.contains(userProjectsList.get(i)))
					{
						reqProjectsList.add(userProjectsList.get(i));
					}*/
					reqProjectsList.add(userProjectsList.get(i));
				}
			}
		}
		return reqProjectsList;
	}
	
	public List<Project> getProjectsRejectsList(List<Project> userProjectsList,List<Collaborator> collabList)
	{
		List<Project> reqProjectsList=new ArrayList<Project>();
		int userProjectsListLen=userProjectsList.size();
		int collabListLen=collabList.size();
		for(int i=0;i<userProjectsListLen;i++)
		{
			for(int j=0;j<collabListLen;j++)
			{
				if(userProjectsList.get(i).getId().equals(collabList.get(j).getPid()))
				{
					/*if(!reqProjectsList.contains(userProjectsList.get(i)))
					{
						reqProjectsList.add(userProjectsList.get(i));
					}*/
					reqProjectsList.add(userProjectsList.get(i));
				}
			}
		}
		return reqProjectsList;
	}
	
}
