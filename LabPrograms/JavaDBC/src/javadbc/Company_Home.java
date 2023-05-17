/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbc;

import java.io.*;
import java.sql.*;

/**
 *
 * @author SHREE
 */
public class Company_Home 
{
    public static void main(String[] args)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/college","root",""); 
            
            // Crete table
            String sqltable = "create table Company_Details(companyid int, companyname varchar(50), companynumber bigint, addressline varchar(500), city varchar(50), state varchar(50), postalcode int, country varchar(50))";
//            PreparedStatement psc = con.prepareStatement(sqltable);
//            int t = psc.executeUpdate();
//            System.out.println("Sucess Query : " + t);

            // Insert Query
            String sqlinsert = "insert into Company_Details values(2, 'Amazon', 456, 'Hyderbad India', 'Telangana', 'India', 4987, 'India')";
//            PreparedStatement ps = con.prepareStatement(sqlinsert);
//            int t = ps.executeUpdate();
//            System.out.println("Sucess Query : " + t);

            // Update Query
            String sqlupdate = "update Company_Details set companyname='MicroSoft' where companyid = 2";
//            PreparedStatement ps = con.prepareStatement(sqlupdate);
//            int t = ps.executeUpdate();
//            System.out.println("Sucess Query : " + t);

            // Delete Query
            String sqldelete = "delete from Company_Details where companyid=2";
            PreparedStatement ps = con.prepareStatement(sqldelete);
            int t = ps.executeUpdate();
            System.out.println("Sucess Query : " + t);
            
            String sqlres = "select * from company_details";
            PreparedStatement ps1 = con.prepareStatement(sqlres);
            ResultSet rs = ps1.executeQuery();
            while(rs.next())
            {
                System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getInt(3) + " | " + rs.getString(4) + " | " + rs.getString(5) + " | " + rs.getString(6) + " | " + rs.getInt(7) + " | " + rs.getString(8));
                
            }     
        }
        catch(Exception e)
        {
            System.out.print("An UnKnown Error occured : \n\n" + e);
        }
    }
}
