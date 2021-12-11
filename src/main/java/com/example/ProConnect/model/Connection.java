package com.example.ProConnect.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.ProConnect.utilities.Utilities;

@Document(collection = "connections")
public class Connection {

	@Id
	private String rid;
	private String from;
	private String to;
	private String status;
	private String time;
	
	@Autowired
	private Utilities utilities;
	
	public Connection() {
		
	}

	public Connection(String from, String to, String status) {
		this.rid = utilities.generateRandomString(20);
		this.from = from;
		this.to = to;
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
	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
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
	
}
