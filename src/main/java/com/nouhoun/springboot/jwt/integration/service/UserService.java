package com.nouhoun.springboot.jwt.integration.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nouhoun.springboot.jwt.integration.domain.User;


public interface UserService {
	List<User> findAllUsers();
	List<User> findByLastName(String s);
	List<User> findByUsername(String s);
	User findById(long i);
	void updateUser(User u, long id);
	List<User> findFreeForm(String criteria, String key);
	List<User> findByFirstName(String lastName);
	User findOneByUsername(String user);
}
