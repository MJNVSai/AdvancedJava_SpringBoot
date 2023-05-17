package com.example.AutowiredFieldInjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeptController 
{
	@Autowired
	private ItDept ob;
	
	public String getDept()
	{
		return ob.getDept();
	}
}
