package com.example.AuthorApplication;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

@Service
public class AuthorService 
{
	private static final Logger log =LoggerFactory.getLogger(AuthorService.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void postConstruct() 
	{
		Author author1=new Author("phani","it");
		Author author2=new Author("ashok","it");
		List <Author> authors = new ArrayList<>();
		authors.add(author1);
		authors.add(author2);
	
		log.info("Creating tables");
		jdbcTemplate.execute("DROP TABLE author IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE author(" + " first_name varchar(255), last_name varchar(255))");
	
		authors.forEach(i->jdbcTemplate.update("INSERT INTO author(first_name, last_name) VALUES (?,?)",i.getFirstName(),i.getLastName()));
		log.info("Records Saved");
		
		//retrieve saved records.
		log.info("Retrieving records");
		authors = jdbcTemplate.query("select * from author", (rs, rowNum)-> new Author(rs.getString("first_name"),rs.getString("last_name")));
		authors.forEach(i -> log.info(i.getFirstName() + " " +i.getLastName()));
	}
}
