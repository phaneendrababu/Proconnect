package com.example.ProConnect.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.ProConnect.utilities.Utilities;

@Document(collection = "projects")
public class Project {

	@Id
	private String id;
	private String email;
	private String title;
	private String content;
	private String link;
	private String type;
	
	@Autowired
	private Utilities utilities;
	
	
	public Project()
	{
		
	}
	
	
	
	public Project(String id, String email, String title, String content, String link, String type) {
		this.id = utilities.generateRandomString(20);
		this.email = email;
		this.title = title;
		this.content = content;
		this.link = link;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId() {
		this.id=utilities.generateRandomString(20);
	}
	
	public void setId(String id) {
		this.id=id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", email=" + email + ", title=" + title + ", content=" + content + ", link=" + link
				+ ", type=" + type + "]";
	}
	
	
	
	
}
