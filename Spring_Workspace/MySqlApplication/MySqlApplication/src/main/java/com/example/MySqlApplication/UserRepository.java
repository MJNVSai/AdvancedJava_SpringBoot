package com.example.MySqlApplication;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository

public class UserRepository 
{
	 // we are autowiring jdbc template, 
	 // using the properties we have configured spring automatically 
	 // detects and creates jdbc template using the configuration
	 @Autowired
	 JdbcTemplate jdbcTemplate;
	 
	 
	 public List<String> getAllUserNames() 
	 {
	  List<String> userNameList = new ArrayList<>();
	  userNameList.addAll(jdbcTemplate.queryForList("select user from login;", String.class));
	  return userNameList;
	 }

}
