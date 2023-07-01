package com.example.emailschedularquartz.web;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

import javax.validation.Valid;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.emailschedularquartz.payload.EmailRequest;
import com.example.emailschedularquartz.payload.EmailResponse;
import com.example.emailschedularquartz.quartz.job.EmailJob;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@RestController
public class EmailSchedularController {
	
	@Autowired
	private Scheduler scheduler;
	
	@PostMapping(value = "/schedule/email", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmailResponse> scheduleEmail(@Valid @RequestBody EmailRequest emailRequest){
		try {
			ZonedDateTime dateTime = ZonedDateTime.of(emailRequest.getDateTime() , emailRequest.getTimeZone());
			
			if(dateTime.isBefore(ZonedDateTime.now())) {
				EmailResponse emailResponse = new EmailResponse(false, 
						"dateTime must be after current time.");
				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(emailResponse);
				
			}
			
			
			JobDetail jobDetail = buildJobDetail(emailRequest);
			Trigger trigger = buildTrigger(jobDetail, dateTime);
			
			scheduler.scheduleJob(jobDetail, trigger);
			
			EmailResponse  emailResponse = new EmailResponse(true,
					jobDetail.getKey().getName(),jobDetail.getKey().getGroup(),"Email Scheduled successfully.");
			
			return ResponseEntity.ok(emailResponse);
			
			
			
		}catch(SchedulerException se) {
			log.error("Error while Scheduling Email" , se);
			
			EmailResponse emailResponse = new EmailResponse(false,
					"Error while scheduling email. Please try again later !");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(emailResponse);
			
		}
		
	}
	
	
	@GetMapping("/schedule/email")
	public ResponseEntity<String>  getApiTest(){
		
		return ResponseEntity.ok("Get Api Test = pass");
	}
	
	
	private JobDetail buildJobDetail(EmailRequest scheduleEmailRequest) {
		
		JobDataMap jobDataMap = new JobDataMap();
		
		jobDataMap.put("email", scheduleEmailRequest.getEmail());
		jobDataMap.put("subject", scheduleEmailRequest.getSubject());
		jobDataMap.put("body", scheduleEmailRequest.getBody());
		
		
		return JobBuilder.newJob(EmailJob.class)
				.withIdentity(UUID.randomUUID().toString(), "email-jobs")
				.withDescription("Send Email job")
				.usingJobData(jobDataMap)
				.storeDurably()
				.build();
	}
	
	
	
	private Trigger buildTrigger(JobDetail jobDetail , ZonedDateTime  startAt) {
		
		return TriggerBuilder.newTrigger()
				.forJob(jobDetail)
				.withIdentity(jobDetail.getKey().getName(), "email")
				.withDescription("Send Email Trigger")
				.startAt(Date.from(startAt.toInstant()))
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
				.build();
		
		
	}

}
