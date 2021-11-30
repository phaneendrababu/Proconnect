package com.example.SpringMongoDB.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "projects")
public class Project {

	@Id
	private String id;
	private String email;
	private String title;
	private String content;
	private String link;
	private String type;
	
	public Project()
	{
		
	}
	
	static String generateRandomString(int n)
    {
  
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
  
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
  
        for (int i = 0; i < n; i++) {
  
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                = (int)(AlphaNumericString.length()
                        * Math.random());
  
            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                          .charAt(index));
        }
  
        return sb.toString();
    }
	
	public Project(String id, String email, String title, String content, String link, String type) {
		this.id = generateRandomString(20);
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
		this.id=generateRandomString(20);
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
