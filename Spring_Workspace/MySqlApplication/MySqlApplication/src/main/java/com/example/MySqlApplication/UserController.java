package com.example.MySqlApplication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(path = "/user")

public class UserController 
{
	 // autowiring user repository
	 @Autowired
	 UserRepository userRepository;

	 /*@GetMapping
	 public String toTest() {
	  return "Welcome to Java Inspires...";
	 }*/


	 //@GetMapping(path = "/getusernames")
	 @GetMapping
	 public List<String> getAllUserNames() 
	 {
	  return userRepository.getAllUserNames();
	 }
}
