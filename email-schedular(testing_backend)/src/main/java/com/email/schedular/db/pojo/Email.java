package com.email.schedular.db.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "emails_details")
@Entity(name = "emails_details")
public class Email {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String emailname;
	private String  emailsubject;
	private String  emailbody;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
	
}
