package com.example.PrimaryAnnotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DeptController 
{
	private Dept ob;
	
	@Autowired
	public DeptController(Dept ob)
	{
		this.ob = ob;
	}
	
	public String getDept()
	{
		return ob.getDept();
	}
}
