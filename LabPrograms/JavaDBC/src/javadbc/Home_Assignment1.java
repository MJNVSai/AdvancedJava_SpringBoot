package javadbc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.sql.*;

/**
 *
 * @author SHREE
 */
public class Home_Assignment1 
{
    public static void main(String[] args)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/student","root",""); 
            Statement st = con.createStatement();
            
            //Create table
            String sqltable = "create table Company_Details(companyid int, companyname varchar(50), companynumber bigint, addressline varchar(500), city varchar(50), state varchar(50), postalcode int, country varchar(50))";
            //st.executeUpdate(sqltable);
            
            // Insert Records
            String sqlinsert = "insert into Company_Details values(2, 'EPAM', 456, 'Hyderbad India', 'Telangana', 'India', 4987, 'India')";
            //st.executeUpdate(sqlinsert);
            
             // Update Query
            String sqlupdate = "update Company_Details set companyname='MicroSoft' where companyid = 1";
            //st.executeUpdate(sqlupdate);
            
            // Delete Query
            String sqldelete = "delete from Company_Details where companyid=1";
            st.executeUpdate(sqldelete);
            
            // Select Query
            ResultSet rs = st.executeQuery("select * from Company_Details");  
            while(rs.next())
            {
                System.out.println(rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getInt(3) + " | " + rs.getString(4) + " | " + rs.getString(5) + " | " + rs.getString(6) + " | " + rs.getInt(7) + " | " + rs.getString(8));
                
            } 
                
            con.close(); 
            
        }
        catch(Exception e)
        {
            System.out.print("An UnKnown Error occured : \n\n" + e);
        }
    }
}
