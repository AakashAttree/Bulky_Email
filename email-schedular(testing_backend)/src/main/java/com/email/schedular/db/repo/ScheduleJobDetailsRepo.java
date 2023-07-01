package com.email.schedular.db.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.email.schedular.db.pojo.ScheduleJobDetails;
@Repository
public interface ScheduleJobDetailsRepo extends JpaRepository<ScheduleJobDetails, Long>{
	
	List<ScheduleJobDetails> findByEmailId(Long emailId);

}
