package com.example.SpringMongoDB.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.SpringMongoDB.model.Posts;
import com.example.SpringMongoDB.model.Profile;
import com.example.SpringMongoDB.model.Project;
import com.example.SpringMongoDB.model.User;
import com.example.SpringMongoDB.repository.PostsRepository;
import com.example.SpringMongoDB.repository.ProfileRepository;
import com.example.SpringMongoDB.repository.ProjectRepository;
import com.example.SpringMongoDB.repository.UserRepository;
import com.example.SpringMongoDB.repository.UserRepositoryCrud;


@Controller
public class UserController {
	
	@Autowired
	private UserRepositoryCrud repositoryCrud;
	@Autowired
	private UserRepository repository;
	@Autowired
	private PostsRepository postsRepository;
	@Autowired
	private ProfileRepository profileRepository;
	@Autowired
	private ProjectRepository projectRepository;
	
	/*ModelAndView mv=new ModelAndView();
	ModelAndView mv1=new ModelAndView();
	ModelAndView mv2=new ModelAndView();*/
	
	@GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user",new User());
        return "registration";
    }
	
	@GetMapping("/login")
	public String showLoginPage(Model model)
	{
		model.addAttribute("user",new User());
        return "login";
	}
	
	@GetMapping("/user-profile/{email}")
	public String showOtherUserProfilePage(@PathVariable("email") String email,@ModelAttribute("user") User user,Model model)
	{
		//model.addAttribute("user", user);
		//model.addAttribute("email",email);
		
		Project p=new Project();
		p.setEmail(email);
		p.setType("Yes");
		Example<Project> e=Example.of(p);
		//System.out.println(e);
		List<Project> py=projectRepository.findAll(e);
		model.addAttribute("ylist", py);
		
		Project p1=new Project();
		p1.setEmail(email);
		p1.setType("No");
		Example<Project> e1=Example.of(p1);
		//System.out.println(e);
		List<Project> pn=projectRepository.findAll(e1);
		model.addAttribute("nlist", pn);
		
		Profile pf=new Profile();
		pf.setEmail(email);
		Example<Profile> ee=Example.of(pf);
		List<Profile> profiles=profileRepository.findAll(ee);
		Profile profile=profiles.get(0);
		//System.out.println( profile.get(0).getProfile());
		model.addAttribute("profile", profile);
		//System.out.println(py);
		System.out.println("In-user-profile");
        return "user-profile";
	}
	
	@GetMapping("/user-dashboard")
	public String showDashboardPage(Model model,@ModelAttribute User user)
	{
		List<Posts> postsList=postsRepository.findAll(Sort.by(Sort.Direction.DESC, "time"));
		//model.addAttribute(attributeValue)
		//mv.addObject("postsList",postsList);
		/*System.out.println(postsList.get(0).getTitle());
		System.out.println(postsList.get(0).getId());*/
		//model.addAttribute("user",user);
        //return "user-dashboard";
		//return mv;
		List<User> userList=repository.findAll();
		userList=removeUser(userList,user);
		model.addAttribute("user",user);
		model.addAttribute("userList", userList);
		model.addAttribute("postsList", postsList);
		return "user-dashboard";
		
	}
	
	
	@PostMapping("/register")    
	public String save(@Valid User user,Errors errors,@ModelAttribute("user") User muser)  
	{    
		System.out.println(errors.getErrorCount());
		if(errors.hasErrors()) {
			return "registration";
		}
		else
		{           
			if(!repository.existsById(muser.getEmail())) {
				repository.save(muser);
				Profile p=new Profile();
				p.setEmail(muser.getEmail());
				p.setCollege("");
				p.setPlace("");
				p.setMobile("");
				p.setSkills("");
				profileRepository.save(p);
				//System.out.println("Added");
				return "user-data";
			}
			else {
				return "redirect:/register";
			}
		}	
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
	
	@PostMapping("/login")
	public String login(@ModelAttribute User user,RedirectAttributes redirectAttrs)
	{
		System.out.println(user.getEmail());
			Example<User> e=Example.of(user);
			List<User> arg=repository.findAll(e);
			List<User> userList=repository.findAll();
			userList=removeUser(userList,user);
			if (arg.size()==1){
				/*mv.setViewName("user-dashboard");
				mv.addObject("user",user);
				mv.addObject("userList",userList);
				
				
				//return "user-dashboard";
				mv1.setViewName("my-profile");
				mv1.addObject("user",user);
				
				mv2.setViewName("create-post");
				mv2.addObject("user",user);
				//System.out.println(mv1);*/
				redirectAttrs.addAttribute("user", user);
				return "redirect:/user-dashboard";
				//return mv;	
			}
			else{
				//return new ModelAndView("login");
				return "redirect:/login";
				//return "login";
			}
	}
	
	@PostMapping("/user-dashboard")
	public void  perform(@ModelAttribute User user)
	{
		//model.addAttribute("user",new User());
		System.out.println(user.getEmail()+"in perform");
        //return "user-dashboard";
		return;
	}
	
}
