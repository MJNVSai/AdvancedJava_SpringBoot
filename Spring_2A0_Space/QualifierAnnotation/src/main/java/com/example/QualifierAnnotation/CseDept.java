package com.example.QualifierAnnotation;

import org.springframework.stereotype.Component;

@Component
public class CseDept implements Dept
{
	@Override
	public String getDept()
	{
		return "This is CSE Department in VRSEC";
	}
}
