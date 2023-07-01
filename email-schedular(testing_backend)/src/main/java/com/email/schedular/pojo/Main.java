/*
 * package com.email.schedular.pojo;
 * 
 * import static org.quartz.CronScheduleBuilder.cronSchedule;
 * 
 * import java.util.ArrayList; import java.util.Iterator; import java.util.List;
 * 
 * import org.quartz.JobDetail; import org.quartz.Trigger; import
 * org.springframework.http.ResponseEntity;
 * 
 * 
 * public class Main {
 * 
 * 
 * 
 * public static List<String> getPatterns(SchedularOption weekSchedularOption){
 * Iterator<Schedule> iterator = weekSchedularOption.getSchedules().iterator();
 * 
 * List<String> list = new ArrayList<String>(); while(iterator.hasNext()) {
 * Schedule schedule = iterator.next(); weekSchedularOption.getMode();
 * list.add(schedule.getTime().getSecond()+" "+schedule.getTime().getMinute()
 * +" "+schedule.getTime().getSecond() +" "+ schedule.getDayOfWeek() +" * *"); }
 * return list;
 * 
 * }
 * 
 * 
 * 
 * // public static List<String> getPatterns(MonthlySchedularOption
 * monthSchedularOption){ // Iterator<Schedule> iterator =
 * monthSchedularOption.getSchedule().iterator(); // // List<String> list = new
 * ArrayList<String>(); // while(iterator.hasNext()) { // Schedule schedule =
 * iterator.next(); // monthSchedularOption.getMode(); //
 * list.add(schedule.getTime().getSecond()+" "+schedule.getTime().getMinute()
 * +" "+schedule.getTime().getSecond() +" "+ schedule.getDayOfWeek()
 * +" "+" * *"); // } // return list; // // }
 * 
 * 
 * public static void main(String[] args) { SchedularOption schedular = new
 * SchedularOption();
 * 
 * 
 * 
 * SchedularOption weekSchedularOption = new SchedularOption();
 * 
 * weekSchedularOption.setMode("Weekly"); List<Schedule> schedules = new
 * ArrayList<>();
 * 
 * Schedule schedule = new Schedule(); schedule.setDayOfWeek("MON"); Time time1
 * = new Time(); time1.setHour("3"); time1.setMinute("12");
 * time1.setSecond("12"); schedule.setTime(time1 ); schedules.add(schedule );
 * 
 * schedule = new Schedule(); schedule.setDayOfWeek("THU"); time1 = new Time();
 * time1.setHour("3"); time1.setMinute("12"); time1.setSecond("12");
 * schedule.setTime(time1 ); schedules.add(schedule );
 * weekSchedularOption.setSchedules(schedules);
 * 
 * //List<String> patterns = getPatterns(weekSchedularOption);
 * 
 * 
 * System.out.println(getPatterns(schedular));
 * 
 * JobDetail jobDetail = buildJobDetail(emailRequest); Trigger trigger =
 * buildTrigger(jobDetail, dateTime);
 * 
 * scheduler.scheduleJob(jobDetail, trigger);
 * 
 * EmailResponse emailResponse = new EmailResponse(true,
 * jobDetail.getKey().getName(),jobDetail.getKey().getGroup()
 * ,"Email Scheduled successfully.");
 * 
 * return ResponseEntity.ok(emailResponse);
 * 
 * 
 * }
 * 
 * }
 */