package com.email.schedular.endpoint;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.email.schedular.db.pojo.ContactEmailRelationship;
import com.email.schedular.db.pojo.Email;
import com.email.schedular.db.pojo.ScheduleJobDetails;
import com.email.schedular.db.repo.ContactEmailRelationshipRepo;
import com.email.schedular.db.repo.EmailRepo;
import com.email.schedular.db.repo.ScheduleJobDetailsRepo;
import com.email.schedular.pojo.EmailDetails;
import com.email.schedular.pojo.EmailRequest;
import com.email.schedular.pojo.EmailResponse;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class EmailSchedularController {

	@Autowired
	private EmailRepo emailRepo;
	
	@Autowired
	private ScheduleJobDetailsRepo  scheduleJobDetailsRepo;
	
	@Autowired
	private ContactEmailRelationshipRepo contactEmailRelationshipRepo;

	@PostMapping(value = "/save/email", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmailResponse> scheduleEmail(@Valid @RequestBody EmailRequest emailRequest){
		try {
			Email email = new Email();
			if(emailRequest.getId() != null) {
				Optional<Email> email2 = emailRepo.findById(emailRequest.getId());
				if(email2.isPresent()) {
					email = email2.get();
				}
			}

			email.setEmailname(emailRequest.getEmailname());
			email.setEmailsubject(emailRequest.getEmailsubject());
			email.setEmailbody(emailRequest.getEmailbody());
			email = emailRepo.save(email);
			EmailResponse emailResponse = new EmailResponse();
			emailResponse.setId(email.getId());
			return ResponseEntity.ok(emailResponse);
		}catch(Exception se) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse());
		}
	}


	@GetMapping("/get/emails")
	public ResponseEntity<List<EmailDetails>>  getEmails(){
		List<EmailDetails> emailDetails = new ArrayList<>();
		List<Email> emails = emailRepo.findAll();
		if(emails!= null) {
			emails.forEach(email ->{
				EmailDetails emailDetail = new EmailDetails();
				emailDetail.setId(email.getId());
				emailDetail.setEmailname(email.getEmailname());
				emailDetail.setEmailsubject(email.getEmailsubject());
				List<ScheduleJobDetails> schedules = scheduleJobDetailsRepo.findByEmailId(email.getId());
				if(schedules != null) {
					emailDetail.setScheduled(!schedules.isEmpty());
				}
				List<ContactEmailRelationship> contactrelation = contactEmailRelationshipRepo.findByEmailid(email.getId());
				if(contactrelation != null) {
					emailDetail.setContactsCount(Long.valueOf(contactrelation.size()));
				}
				else {
					emailDetail.setContactsCount(0L);
				}
				emailDetails.add(emailDetail);
			});
		}
		return ResponseEntity.ok(emailDetails);
	}
	@GetMapping("/get/email/{emailId}")
	public ResponseEntity<Email>  getEmail(@PathVariable("emailId") Long emailId){

		Optional<Email> email = emailRepo.findById(emailId);
		if(email.isPresent()) {
			return ResponseEntity.ok(email.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Email());
		}
		
	}
}
