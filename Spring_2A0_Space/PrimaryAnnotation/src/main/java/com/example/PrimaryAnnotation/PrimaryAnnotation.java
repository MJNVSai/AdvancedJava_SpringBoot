package com.example.PrimaryAnnotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class PrimaryAnnotation 
{
	public static void main(String[] args) 
	{
		ConfigurableApplicationContext context=SpringApplication.run(PrimaryAnnotation.class,args);		
		DeptController ob1 = (DeptController)context.getBean(DeptController.class);
		System.out.println("\n");
		System.out.println(ob1.getDept());
		System.out.println("\n");
	}
}
