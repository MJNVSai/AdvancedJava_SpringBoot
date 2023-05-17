package com.example.Components;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.Components.*;

@SpringBootApplication
public class ComponentsApplication {

	public static void main(String[] args) 
	{
		//SpringApplication.run(ComponentsApplication.class, args);
		ConfigurableApplicationContext context=SpringApplication.run(ComponentsApplication.class,args);		
		DeptController ob1=(DeptController)context.getBean(DeptController.class);
		System.out.println(ob1.getDept()); 
	}

}
