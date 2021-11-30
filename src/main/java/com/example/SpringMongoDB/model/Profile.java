package com.example.SpringMongoDB.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "profile")
public class Profile {

	@Id
	private String email;
	private String college;
	private String place;
	private String mobile;
	private String skills;
	
	public Profile() {
		
	}
	public Profile(String email, String college, String place,String mobile,String skills) {
		this.email = email;
		this.college = college;
		this.place = place;
		this.mobile=mobile;
		this.skills=skills;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	@Override
	public String toString() {
		return "Profile [email=" + email + ", college=" + college + ", place=" + place + ", mobile=" + mobile
				+ ", skills=" + skills + "]";
	}
	
}
