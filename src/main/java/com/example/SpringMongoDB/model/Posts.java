package com.example.SpringMongoDB.model;

import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
	public Posts()
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
	
	public String generateTime()
	{
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		this.time = myDateObj.format(myFormatObj);
		return time;
	}
	
	public Posts(String id,String email,String name,String title, String content,List<String> comments,int likes, String time) {
		this.id=generateRandomString(20);
		this.email = email;
		this.name=name;
		this.title = title;
		this.content = content;
		this.comments=comments;
		this.likes=likes;
		this.time=generateTime();
	}
	
	
	
	
	public String getTime() {
		return time;
	}

	public void setTime() {
		this.time=generateTime();
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
		this.id=generateRandomString(20);
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
