package com.email.schedular.endpoint;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
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

import com.email.schedular.config.QuartzConfig;
import com.email.schedular.db.pojo.ScheduleJobDetails;
import com.email.schedular.db.repo.ScheduleJobDetailsRepo;
import com.email.schedular.jobs.EmailJob;
import com.email.schedular.pojo.SchedularOption;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class SchedularEndPoint {

	private static final String GROUP1 = "group1";
	@Autowired
	private SchedularOptionService service ;
	@Autowired
	private QuartzConfig quartzConfig;
	@Autowired
	private ScheduleJobDetailsRepo scheduleJobDetailsRepo;


	@GetMapping("/get")
	public String get() {
		return "Success";
	}

	@PostMapping(value = "/schedule/{emailId}", 
			consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.TEXT_HTML_VALUE})
	public ResponseEntity<String> post(@RequestBody SchedularOption obj,
			@PathVariable("emailId") Long emailId) {
		List<String> patterns = service.getPatterns(obj);
		try { 
		patterns.forEach(pattern->{
			
			String[] patternValues = pattern.split(" ");
			String jobName = obj.getName();
			ScheduleJobDetails scheduleJobDetails = new ScheduleJobDetails(); 
			scheduleJobDetails.setMode(obj.getMode());
			scheduleJobDetails.setEmailId(emailId);
			scheduleJobDetails.setJobGroupName(GROUP1);
			scheduleJobDetails.setJobName(jobName);
			scheduleJobDetails.setSecond(patternValues[0]);
			scheduleJobDetails.setMinute(patternValues[1]);
			scheduleJobDetails.setHour(patternValues[2]);
			scheduleJobDetails.setDayOfMonth(patternValues[3]);
			scheduleJobDetails.setMonth(patternValues[4]);
			scheduleJobDetails.setDayOfWeek(patternValues[5]);
			
			JobDetail jobDetail = JobBuilder.newJob(EmailJob.class)
					.withIdentity(jobName, GROUP1).build();

			// Adding JobDataMap to jobDetail

				try {
					jobDetail.getJobDataMap().put("emailId", String.valueOf(emailId));
					CronTrigger trigger = newTrigger()
							.withIdentity("trigger_"+jobName , GROUP1)
							.withSchedule(cronSchedule(pattern))
							.forJob(jobName, GROUP1)
							.build();
					Scheduler scheduler = quartzConfig.schedulerFactoryBean().getScheduler();
					
					if(scheduler.checkExists(trigger.getJobKey())) {
						scheduler.deleteJob(trigger.getJobKey());
					}
					scheduler.scheduleJob(jobDetail, trigger);
					Date nextFireTime = trigger.getNextFireTime();
					scheduleJobDetails.setNextExecutionTime(LocalDateTime.of(nextFireTime.getYear(), nextFireTime.getMonth(), nextFireTime.getDate(), 23, 59, 59));
					scheduleJobDetailsRepo.save(scheduleJobDetails);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SchedulerException e) {
					e.printStackTrace();
				}
			
		});
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
		}

		return ResponseEntity.status(HttpStatus.OK).body("");
	}
	
	@GetMapping(value = "get/schedule/{emailId}")
	public ResponseEntity<List<ScheduleJobDetails>> getSchedules(@PathVariable("emailId") Long emailId)
	{
		List<ScheduleJobDetails> schedules = scheduleJobDetailsRepo.findByEmailId(emailId);
		return ResponseEntity.status(HttpStatus.OK).body(schedules);
	}


}
