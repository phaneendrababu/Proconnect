package com.example.ProConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ProConnect.model.Posts;
import com.example.ProConnect.model.User;
import com.example.ProConnect.repository.PostsRepository;
import com.example.ProConnect.repository.UserRepository;

@Service
public class PostService {

	@Autowired
	private UserRepository UserRepository;
	@Autowired
	private PostsRepository PostsRepository;
	
	public void showCreatePostPage(User user,Model model)
	{
		Example<User> example=Example.of(user);
		List<User> UsersList=UserRepository.findAll(example);
		model.addAttribute("name",UsersList.get(0).getName());	
	}
	
	public void createPost(Posts post)
	{
		post.setId();
		post.setTime();
		PostsRepository.save(post);
	}
	
	
	public Posts getPost(List<Posts> posts,String id) {
		
		for(int i=0;i<posts.size();i++) {
			if(posts.get(i).getId().equals(id)) {
				return posts.get(i);
			}
		}
		return posts.get(0);
	}
	
	public void showCommentPage(String id,Model model)
	{
		List<Posts> posts = PostsRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		Posts post=getPost(posts,id);
		List<String> prevComments=post.getComments();
		model.addAttribute("post", post);
		model.addAttribute("prevComments",prevComments);
	}
	
	
	public void showMyPosts(String userEmail,Model model)
	{
		
		Posts post=new Posts();
		post.setEmail(userEmail);
		Example<Posts> postsExample=Example.of(post);
		List<Posts> myPostsList=PostsRepository.findAll(postsExample);
		
		model.addAttribute("userEmail",userEmail);
		model.addAttribute("myPostsList",myPostsList);
	}
	
	public void deletePost(String userEmail,String id,Model model,RedirectAttributes redircetAttrs)
	{
		PostsRepository.deleteById(id);
		redircetAttrs.addAttribute("uid", userEmail);
	}
	
	public void addComment(String id,String email,Posts post,RedirectAttributes redirectAttrs)
	{
		System.out.println("comment-added");
		List<Posts> posts = PostsRepository.findAll();
		Posts p=getPost(posts,post.getId());
		List<String> prevComments=p.getComments();
		prevComments.addAll(post.getComments());
		post.setComments(prevComments);
		post.setId(id);
		post.setTime(p.getTime());
		PostsRepository.save(post);
		
		User user=new User();
		
		user.setEmail(email);
		redirectAttrs.addAttribute("user",user);
	}
}
