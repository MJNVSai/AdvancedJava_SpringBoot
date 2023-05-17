package com.example.CustomerDetails;

public class CDetails 
{
	private String cno, cname, cmail, caddress;
	
	public String getCno() 
	{
		return cno;
	}
	public void setCno(String cno) 
	{
		this.cno = cno;
	}
	
	public String getCname()
	{
		return cname;
	}
	public void setCname(String cname)
	{
		this.cname = cname;
	}
	
	public String getCmail()
	{
		return cmail;
	}
	public void setCmail(String cmail)
	{
		this.cmail = cmail;
	}
	
	public String getCaddress()
	{
		return caddress;
	}
	public void setCaddress(String caddress)
	{
		this.caddress = caddress;
	}
	
	public CDetails(String cno, String cname, String cmail, String caddress)
	{
		super();
		this.cno = cno;
		this.cname = cname;
		this.cmail = cmail;
		this.caddress = caddress;
	}
}
