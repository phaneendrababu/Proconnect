package com.example.ProConnect.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.ProConnect.utilities.Utilities;
@Document(collection = "collaborators")
public class Collaborator {

	@Id
	private String rid;
	private String fromEmail;
	private String pid;
	private String status;
	private String time;
	
	
	@Autowired
	private Utilities utilities;
	
	public Collaborator() {
		
	}

	public Collaborator(String fromEmail, String pid, String status) {
		this.rid = utilities.generateRandomString(20);
		this.fromEmail = fromEmail;
		this.pid = pid;
		this.status = status;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid)
	{
		this.rid=rid;
	}
	
	public void setRid() {
		this.rid = utilities.generateRandomString(20);
	}


	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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


	@Override
	public String toString() {
		return "Collaborator [rid=" + rid + ", fromEmail=" + fromEmail + ", pid=" + pid + ", status=" + status
				+ ", time=" + time + "]";
	}

	
}
