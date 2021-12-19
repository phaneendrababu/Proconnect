package com.example.ProConnect.model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "connections")
public class Connection {

	@Id
	private String rid;
	private String from;
	private String to;
	private String status;
	private String time;
	
	
	public Connection() {
		
	}

	public Connection(String from, String to, String status) {
		this.rid = generateRandomString(20);
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
		this.rid = generateRandomString(20);
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
		this.time=generateTime();
	}
	public void setTime(String time) {
		this.time=time;
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
