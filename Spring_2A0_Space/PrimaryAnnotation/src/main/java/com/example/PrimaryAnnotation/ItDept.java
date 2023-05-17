package com.example.PrimaryAnnotation;

import org.springframework.stereotype.Component;

@Component
public class ItDept implements Dept
{
	@Override
	public String getDept()
	{
		return "This is IT Department in VRSEC";
	}
}
