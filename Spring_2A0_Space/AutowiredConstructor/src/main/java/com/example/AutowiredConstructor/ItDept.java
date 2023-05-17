package com.example.AutowiredConstructor;

import org.springframework.stereotype.Component;

@Component
public class ItDept 
{
	public String getDept()
	{
		return "This Is IT Department in Constructor Injection";
	}
}
