package com.email.schedular.jobs;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
//import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.email.schedular.db.pojo.Contact;
import com.email.schedular.db.pojo.ContactEmailRelationship;
import com.email.schedular.db.pojo.Email;
import com.email.schedular.db.repo.ContactEmailRelationshipRepo;
import com.email.schedular.db.repo.ContactRepo;
import com.email.schedular.db.repo.EmailRepo;
import com.email.schedular.db.repo.ScheduleJobDetailsRepo;

public class EmailJob extends QuartzJobBean {
	
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MailProperties mailProperties;
	@Autowired
	private ContactEmailRelationshipRepo contactEmailRelationshipRepo;
	@Autowired
	private ContactRepo contactRepo;
	@Autowired
	private EmailRepo emailRepo;
	@Autowired
	private ScheduleJobDetailsRepo scheduleJobDetailsRepo;
	
	
@Override
protected void executeInternal(JobExecutionContext jobExecutionContext)  {
	
	JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
	
	
	String emailId = jobDataMap.getString("emailId");
	long parseLong = Long.parseLong(emailId);
	Optional<Email> emailDetails = emailRepo.findById(parseLong);
	if(emailDetails.isPresent()) {
		Email emailDetail = emailDetails.get();
		List<ContactEmailRelationship> relations = contactEmailRelationshipRepo.findByEmailid(parseLong);
		relations.forEach(realtion->{
			Optional<Contact> contact = contactRepo.findById(realtion.getContactid());
			if(contact.isPresent()) {
				
				sendMail(mailProperties.getUsername() , contact.get().getEmail() , emailDetail.getEmailsubject() , emailDetail.getEmailbody());
			}
		});
		
	}
	
	

	
}



private void sendMail(String fromEmail , String toEmail , String subject , String body) {
	try {
		MimeMessage message = mailSender.createMimeMessage();
		
		MimeMessageHelper messageHelper = new MimeMessageHelper(message , StandardCharsets.UTF_8.toString());
		messageHelper.setSubject(subject);
		messageHelper.setText(body, true);
		messageHelper.setFrom(fromEmail);
		messageHelper.setTo(toEmail);
		
		mailSender.send(message);
		
		
	}catch(MessagingException ex) {
		System.out.println(ex);
		
		
	}
	
}

}
