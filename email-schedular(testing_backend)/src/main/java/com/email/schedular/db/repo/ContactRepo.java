package com.email.schedular.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.email.schedular.db.pojo.Contact;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Long>{
	Contact findByEmail(String email);
}
