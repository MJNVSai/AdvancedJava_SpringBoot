package com.example.annotations;
import com.example.annotations.*;
import com.example.annotations.ITDept;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class AnnotationsApplication {

	public static void main(String[] args) {
		//SpringApplication.run(AnnotationsApplication.class, args);
		//ApplicationContext context= new AnnotationConfigApplicationContext(AnnotationsApplication.class);
		//var context.ConfigurableApplicationContext=SpringApplication.run(AnnotationsApplication.class, args);
		//var context:ConfigurableApplicationContext=SpringApplication.run(AnnotationsApplication.class,args);
				
		ConfigurableApplicationContext context=SpringApplication.run(AnnotationsApplication.class,args);
		ITDept ob=context.getBean(ITDept.class);
		System.out.println(ob.getDept());
	}

}



