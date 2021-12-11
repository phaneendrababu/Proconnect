package com.example.ProConnect.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.ProConnect.utilities.Utilities;

@Document(collection = "posts")
public class Posts {

	@Id
	private String id;
	private String email;
	private String name;
	private String title;
	private String content;
	private List<String> comments;
	private int likes;
	private String time;
	
	@Autowired
	private Utilities utilities;
	
	public Posts()
	{
		
	}
	
	
	public Posts(String id,String email,String name,String title, String content,List<String> comments,int likes, String time) {
		this.id=utilities.generateRandomString(20);
		this.email = email;
		this.name=name;
		this.title = title;
		this.content = content;
		this.comments=comments;
		this.likes=likes;
		this.time=utilities.generateTime();
	}
	
	
	
	
	public String getTime() {
		return time;
	}

	public void setTime() {
		this.time=utilities.generateTime();
	}
	public void setTime(String time) {
		this.time=time;
	}
	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments=comments;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId() {
		this.id=utilities.generateRandomString(20);
	}
	
	public void setId(String id) {
		this.id=id;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	@Override
	public String toString() {
		return "Posts [id=" + id + ", email=" + email + ", name=" + name + ", title=" + title + ", content=" + content
				+ ", comments=" + comments + ", likes=" + likes + ", time=" + time + "]";
	}
	
}
