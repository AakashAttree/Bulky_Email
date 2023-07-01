package com.email.schedular.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.email.schedular.db.pojo.Email;

@Repository
public interface EmailRepo extends JpaRepository<Email, Long>{

}
