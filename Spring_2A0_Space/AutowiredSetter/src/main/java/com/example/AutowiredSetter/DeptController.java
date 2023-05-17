package com.example.AutowiredSetter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeptController 
{
	private ItDept ob;
	
	@Autowired
	public void setDept(ItDept ob)
	{
		this.ob = ob;
	}
	
	public String getDept()
	{
		return ob.getDept();
	}
}
