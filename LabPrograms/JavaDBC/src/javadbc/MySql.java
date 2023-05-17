/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbc;
import java.io.*;
import java.sql.*;
import java.util.*;
/**
 *
 * @author SHREE
 */
public class MySql 
{
    public static void main(String[] args)
    {
        try
        {
            Scanner s = new Scanner(System.in);
            System.out.print("Enter Eno : ");
            int a = s.nextInt();
            System.out.print("Enter Ename : ");
            String ss = s.next();
            
            Class.forName("com.mysql.jdbc.Driver"); 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/208w1a12a0","root",""); 
            Statement st = con.createStatement();
            // Statement is an Interface and Connection is an Child Class
            
            //Create Database
            String sqldb = "create database 208W1A12A0";
            //st.executeUpdate(sqldb);
            
            //Create table
            String sqltable = "create table employee (eno int, name varchar(20))";
            //st.executeUpdate(sqltable);
            
            // Insert Records
            String sqlinsert = "insert into employee values(4,'kumar')";
            //st.executeUpdate(sqlinsert);
            String insertdynamic = "insert into employee values("+a+", '"+ss+"')";
            //st.executeUpdate(insertdynamic);
            
            // Update Query
            String sqlupdate = "update employee set name='charan' where eno=268";
            //st.executeUpdate(sqlupdate);
            
            // Delete Query
            String sqldelete = "delete from employee where eno=299";
            st.executeUpdate(sqldelete);
            
            // Select Query
            ResultSet rs = st.executeQuery("select * from employee");  
            while(rs.next())  
                System.out.println(rs.getInt(1) + "  " + rs.getString(2));  
            con.close();  

        }
        catch(Exception e)
        {
            System.out.println("An unknown Error occured : \n" + e);
        }
    }
}
