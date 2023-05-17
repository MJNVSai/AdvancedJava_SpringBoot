package com.example.CustomerDetails;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;

@Service
public class CService 
{
	private static final Logger log = LoggerFactory.getLogger(CService.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void postConstruct() 
	{
		CDetails cd1 = new CDetails("1", "Prabhas", "actorprabhas@gmail.com", "Indian Film Industry, India");
		CDetails cd2 = new CDetails("2", "Ram Charan", "actorramcharan@gmail.com", "Indian Film Industry, India");
		
		List<CDetails> customers = new ArrayList<>();
		customers.add(cd1);
		customers.add(cd2);
		
		log.info("<--------------------------  Creating tables -------------------------------------->");
		jdbcTemplate.execute("DROP TABLE Customer IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE Customer(" + " cno varchar(255), cname varchar(255), cmail varchar(255), caddress varchar(255))");
		
		customers.forEach(i->jdbcTemplate.update("INSERT INTO Customer VALUES (?, ?, ?, ?)", i.getCno(), i.getCname(), i.getCmail(), i.getCaddress()));
		log.info("<-------------------------  Records Saved  ----------------------------------->");
		
		//retrieve saved records.
		log.info("<-------------------------  Retrieving records  ------------------------------------->");
		customers = jdbcTemplate.query("select * from Customer", (rs, rowNum)-> new CDetails(rs.getString("cno"), rs.getString("cname"), rs.getString("cmail"), rs.getString("caddress")));
		customers.forEach(i -> log.info(i.getCno() + " | " + i.getCname() + " | " + i.getCmail() + " | " + i.getCaddress()));
	}
}
