package com.email.schedular.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.email.schedular.pojo.SchedularOption;

@Service
public class SchedularOptionService {
private static final String PATTERN = "%s %s %s %s %s %s %s";

	/*
 
 				String value = String.format("%s %s %s %s %s %s", 
						"*",
						"*",
						"*",
						"*",
						"*",
						"*");
 
 */
	public List<String> getPatterns(SchedularOption options) {
		List<String> patterns = new ArrayList<>();
		switch (options.getMode().toUpperCase()) {
		case "ONCE":
			options.getSchedules().forEach(schedule -> {
				
				String[] dateValues = schedule.getDate().split("-");
				
				String value = String.format(PATTERN, 
						schedule.getTime().getSecond(),
						schedule.getTime().getMinute(),
						schedule.getTime().getHour(),
						dateValues[1],
						dateValues[2],
						"?",
						dateValues[0]);
				patterns.add(value);
			});
			break;
		case "DAILY":
			options.getSchedules().forEach(schedule -> {
				String value = String.format(PATTERN, 
						schedule.getTime().getSecond(),
						schedule.getTime().getMinute(),
						schedule.getTime().getHour(),
						"*",
						"*",
						"?",
						"*");
				patterns.add(value);

			});
			break;
		case "WEEKLY":
			options.getSchedules().forEach(schedule -> {
				String value = String.format(PATTERN, 
						schedule.getTime().getSecond(),
						schedule.getTime().getMinute(),
						schedule.getTime().getHour(),
						"?",
						"*",
						getDayOfWeek(schedule.getDayOfWeek()),
						"*");
				patterns.add(value);

			});
			break;
		case "MONTHLY":
			options.getSchedules().forEach(schedule -> {

				String value = String.format(PATTERN, 
						schedule.getTime().getSecond(),
						schedule.getTime().getMinute(),
						schedule.getTime().getHour(),
						schedule.getDayOfMonth(),
						schedule.getMonth(),
						"?",
						"*");
				patterns.add(value);

			});
			break;
		default:
			break;
		}

		return patterns;
	}

private String getDayOfWeek(String dayOfWeek) {
	String result = "";
	switch (dayOfWeek.toUpperCase()) {
	case "MONDAY":
		result = "MON";
		break;
	case "TUESDAY":
		result = "TUE";
		break;
	case "WEDNESDAY":
		result = "WED";
		break;
	case "THRUSDAY":
		result = "THR";
		break;
	case "FRIDAY":
		result = "FRI";
		break;
	case "SATURDAY":
		result = "SAT";
		break;
	case "SUNDAY":
		result = "SUN";
		break;
	default:
		result = dayOfWeek;
		break;
	}
	return result;
}
}
