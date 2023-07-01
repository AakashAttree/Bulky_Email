package com.example.emailschedularquartz.payload;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class EmailRequest {
	@Email
	@NotEmpty
	private String email;

	
	@NotEmpty
	private String  subject;
	
	
	@NotEmpty
	private String  body;
	
	@NotNull
	private LocalDateTime dateTime;
	
	
	@NotNull
	private ZoneId timeZone;
	
	public static void main(String[] args) {
		LocalDateTime dateTime =  LocalDateTime.now();
		System.out.println(dateTime);
	}
}
