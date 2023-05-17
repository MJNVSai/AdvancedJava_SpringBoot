package com.example.Components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeptController 
{
	private ItDept ob;
	@Autowired
	public DeptController(ItDept ob)
	{
		this.ob=ob;
	}
	public String getDept()
	{
		return ob.getDept();
	}

}
