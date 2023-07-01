
package com.email.schedular.pojo;

import java.util.List;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class SchedularOption {
	private Long id;
	private String name;
    private String mode;
    private List<Schedule> schedules = null;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    
}
