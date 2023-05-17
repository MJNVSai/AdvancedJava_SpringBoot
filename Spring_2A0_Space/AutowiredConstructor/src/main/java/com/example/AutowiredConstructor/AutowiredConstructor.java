package com.example.AutowiredConstructor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class AutowiredConstructor 
{
	public static void main(String[] args)
	{
		ConfigurableApplicationContext context=SpringApplication.run(AutowiredConstructor.class,args);
		DeptController ob1 = (DeptController)context.getBean(DeptController.class);
		System.out.print("\n");
		System.out.println(ob1.getDept());
		System.out.print("\n");
	}
}
