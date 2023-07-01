package com.email.schedular.db.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "contact_email")
@Entity(name = "contact_email")
public class ContactEmailRelationship {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long emailid ;
	private Long contactid;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEmailid() {
		return emailid;
	}
	public void setEmailid(Long emailid) {
		this.emailid = emailid;
	}
	public Long getContactid() {
		return contactid;
	}
	public void setContactid(Long contactid) {
		this.contactid = contactid;
	}
	
	
}
