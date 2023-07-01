package com.email.schedular.pojo;

public class EmailDetails {
	private Long id;
	private String emailname;
	private String  emailsubject;
	private String  emailbody;
	private boolean scheduled;
	private Long contactsCount;
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
	public boolean isScheduled() {
		return scheduled;
	}
	public void setScheduled(boolean scheduled) {
		this.scheduled = scheduled;
	}
	public Long getContactsCount() {
		return contactsCount;
	}
	public void setContactsCount(Long contactsCount) {
		this.contactsCount = contactsCount;
	}
	
}
