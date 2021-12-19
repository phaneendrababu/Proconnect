package com.example.ProConnect.model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "collaborators")
public class Collaborator {

	@Id
	private String rid;
	private String fromEmail;
	private String pid;
	private String status;
	private String time;
	
	
	
	
	public Collaborator() {
		
	}

	public Collaborator(String fromEmail, String pid, String status) {
		this.rid = generateRandomString(20);
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
		this.rid = generateRandomString(20);
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
		this.time=generateTime();
	}
	public void setTime(String time) {
		this.time=time;
	}


	@Override
	public String toString() {
		return "Collaborator [rid=" + rid + ", fromEmail=" + fromEmail + ", pid=" + pid + ", status=" + status
				+ ", time=" + time + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static String generateRandomString(int n)
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
	
	public static String generateTime()
	{
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		return myDateObj.format(myFormatObj);
	}
	
}
