package com.example.SpringMongoDB.model;



import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class User {

	
	@Id
	@Email(message ="Please Enter Valid Email")
	private String email;
	@NotEmpty(message="Name Should not be Empty")
	private String name;
	@NotEmpty(message="Password Should not be Empty")
	private String password;
	public User() {
		
	}

	
	
	
	
	public User(String email,String name,String password) {
		this.email = email;
		this.name = name;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "User [email=" + email + ", name=" + name + ", password=" + password + "]";
	}
	
	
}
