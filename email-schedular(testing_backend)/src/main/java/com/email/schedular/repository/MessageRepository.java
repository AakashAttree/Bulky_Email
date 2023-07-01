package com.email.schedular.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.email.schedular.model.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

}