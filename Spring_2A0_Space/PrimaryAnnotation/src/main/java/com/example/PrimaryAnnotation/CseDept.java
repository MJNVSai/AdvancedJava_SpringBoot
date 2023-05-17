package com.example.PrimaryAnnotation;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class CseDept implements Dept
{
	@Override
	public String getDept()
	{
		return "Primary Annotation , This is CSE Department in VRSEC";
	}
}
