package com.example.SpringMongoDB.controller;


import java.util.*;

import javax.crypto.spec.PSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


@Controller
public class ProfileController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProfileRepository profileRepository;
	@Autowired
	private PostsRepository postsRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private UserController uc;
	
	MongoTemplate mongoTemplate;
	
	@GetMapping("/my-profile/{email}")
	public String showProfile(@PathVariable("email") Profile email,@ModelAttribute("user") User user,Model model)
	{
		//@PathVariable("email") Profile email
		//modelAndView.addObject("profile", new Profile());
		//mv.setViewName("my-profile");
		//return uc.mv1;
		//model.addAttribute("user", user);
		Example<Profile> e=Example.of(email);
		List<Profile> pf=profileRepository.findAll(e);
		model.addAttribute("college", pf.get(0).getCollege());
		model.addAttribute("place", pf.get(0).getPlace());
		model.addAttribute("mobile",pf.get(0).getMobile());
		model.addAttribute("skills",pf.get(0).getSkills());
		System.out.println(user.getEmail()+" in Profile");
		System.out.println(pf.get(0).getSkills());
		//model.addAttribute("email",user.getEmail());
		return "my-profile";
	}
	
	@GetMapping("/create-post/{email}")
	public String showCreatePostPage(@ModelAttribute("user") User user,Model model)
	{
		model.addAttribute("posts", new Posts());
		//return "create-post";
		//uc.mv2.addObject("count",count);
		//return uc.mv2;
		Example<User> e=Example.of(user);
		List<User> lu=userRepository.findAll(e);
		model.addAttribute("name",lu.get(0).getName());
		
		return "create-post";
	}
	
	public Posts getPost(List<Posts> posts,String id) {
		
		for(int i=0;i<posts.size();i++) {
			if(posts.get(i).getId().equals(id)) {
				return posts.get(i);
			}
		}
		return posts.get(0);
	}
	
	@GetMapping("/comment/{email}/{id}")
	public String showCommentPage(@ModelAttribute("email") String email,@PathVariable(name = "id")  String id,Model model)
	{
		/*model.addAttribute("posts", new Posts());
		return "create-post";*/
		System.out.println("Email:"+email);
		List<Posts> posts = postsRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
		Posts post=getPost(posts,id);
		List<String> prevComments=post.getComments();
		model.addAttribute("post", post);
		model.addAttribute("prevComments",prevComments);
		model.addAttribute("post_comment", new Posts());
		//System.out.println(prevComments);
		return "post-comment";
	}
	
	@GetMapping("/like/{id}")
	public String likePage(@ModelAttribute("user") User user,@ModelAttribute("post_comment") Posts post, RedirectAttributes redirectAttrs)
	{
		//redirectAttrs.addAttribute("user", user);
		return "user-dashboard";
	}
	
	@PostMapping("/my-profile/{email}")
	public String profileUpdate(@PathVariable("email") String email,@ModelAttribute("user") User user,@ModelAttribute("profile") Profile profile,RedirectAttributes redirectAttrs) {
		System.out.println("-Updated-");
		profileRepository.save(profile);
		/*RedirectView rv=new RedirectView();
		rv.setContextRelative(true);
		rv.setUrl("/user-dashboard");
		return rv;
		return "redirect:/user-dashboard";
		return uc.mv;*/
		redirectAttrs.addAttribute("user", user);
		return "redirect:/user-dashboard";
	}
	
	
	@PostMapping("/create-post/{email}")
	public String createPost(@ModelAttribute("user") User user,@ModelAttribute("posts") Posts posts,RedirectAttributes redirectAttrs) 
	{
		System.out.println("post-created");
		System.out.println(posts.toString());
		posts.setId();
		posts.setTime();
		postsRepository.save(posts);
		redirectAttrs.addAttribute("user", user);
		return "redirect:/user-dashboard";
	}
	
	
	@PostMapping("/comment/{email}/{id}")
	public String addComment(@PathVariable("id") String id,@ModelAttribute("email") String email,@ModelAttribute("post_comment") Posts post, RedirectAttributes redirectAttrs) 
	{
		System.out.println("comment-added");
		//System.out.println(post.getCount()+" "+post.getTitle()+" "+post.getCount()+" "+post.getComments());
		System.out.print(post.toString());
		List<Posts> posts = postsRepository.findAll();
		Posts p=getPost(posts,post.getId());
		List<String> prevComments=p.getComments();
		prevComments.addAll(post.getComments());
		post.setComments(prevComments);
		post.setId(id);
		post.setTime(p.getTime());
		//postsRepository.deleteById(new Long(post.getCount()));
		//postsRepository.deleteById(id);
		postsRepository.save(post);
		//postsRepository.save(post);
		
		User user=new User();
		
		user.setEmail(email);
		redirectAttrs.addAttribute("user",user);
		System.out.println(user.getEmail());
		return "redirect:/user-dashboard";
	}
	
	@GetMapping("/add-project/{email}")
	public String showAddProjectPage(@ModelAttribute("user") User user,Model model) {
		return "add-project";
	}
	

	@PostMapping("/add-project/{email}")
	public String addProject(@ModelAttribute("user") User user,@ModelAttribute("project") Project project,RedirectAttributes redirectAttrs) {
		project.setId();
		System.out.println(project.toString());
		projectRepository.save(project);
		redirectAttrs.addAttribute("user",user);
		return "redirect:/user-dashboard";
	}
	
	/*@GetMapping("/login")
	public ModelAndView loginPage()
	{
		ModelAndView mav=new ModelAndView("login");
        mav.addObject("user",new User());
        return mav;
	}*/
	
	/*@GetMapping("/logout")
	public ModelAndView logout(ModelAndView mv)
	{
		return mv;
	}*/
	
	/*@PostMapping("/user-dashboard")
	public ModelAndView login(@ModelAttribute User user)
	{
		System.out.println(user.getEmail());
		try {
			//boolean arg=loginRepository.existsById(user.getEmail())
			Example<User> e=Example.of(user);
			List<User> arg=loginRepository.findAll(e);
			if (arg.size()==1){
				ModelAndView mv=new ModelAndView();
				mv.setViewName("user-dashboard");
				mv.addObject("user",user);
				System.out.println("e=1");
				//return "redirect:/user-dashboard";
				return mv;	
			}
			else{
				return new ModelAndView("login");
				//return "redirect:/login";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return new ModelAndView("login");
		//return "redirect:/login";
	}*/
	
	
	/*@PostMapping("/user-data")
	public String saveUser(@RequestBody User user) {
		repository.save(user);
		return "Added book with email : ";
	}*/
	
	/*@GetMapping("/add1")
	public String add() {
		return "add1";
	}
	
	@GetMapping("/addUser")
	public String login(Model model, User user) {
    	model.addAttribute("email", user.getEmail());
        model.addAttribute("password", user.getPassword());
        return "addUser";
    }
	@GetMapping("/add")
	public String add() {
		return "add";
	}
	@PostMapping("/add")
	public String saveBook(@RequestBody User user) {
		repository.save(user);
		return "Added book with email : ";
	}/

	/*@GetMapping("/findAllBooks")
	public List<User> getBooks() {
		return repository.findAll();
	}

	@GetMapping("/findAllBooks/{id}")
	public Optional<User> getBook(@PathVariable int id) {
		return repository.findById(id);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteBook(@PathVariable int id) {
		repository.deleteById(id);
		return "book deleted with id : " + id;
	}*/

}
