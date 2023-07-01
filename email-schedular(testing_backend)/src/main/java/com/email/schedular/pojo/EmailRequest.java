package com.email.schedular.pojo;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class EmailRequest {
	
	private Long id;
	
	private String emailname;

	
	@NotEmpty
	private String  emailsubject;
	
	
	@NotEmpty
	private String  emailbody;


	public String getEmailname() {
		return emailname;
	}


	public void setEmailname(String emailname) {
		this.emailname = emailname;
	}


	public String getEmailsubject() {
		return emailsubject;
	}


	public void setEmailsubject(String emailsubject) {
		this.emailsubject = emailsubject;
	}


	public String getEmailbody() {
		return emailbody;
	}


	public void setEmailbody(String emailbody) {
		this.emailbody = emailbody;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
}
