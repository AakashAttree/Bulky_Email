package com.email.schedular.db.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.email.schedular.db.pojo.ContactEmailRelationship;

@Repository
public interface ContactEmailRelationshipRepo extends JpaRepository<ContactEmailRelationship, Long>{
	List<ContactEmailRelationship> findByEmailid(Long emailId);
}
