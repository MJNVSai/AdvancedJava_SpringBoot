/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbc;
import java.io.*;
import java.util.*;
import java.sql.*;

/**
 *
 * @author SHREE
 */
public class PrepState 
{
    public static void main(String[] args)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/student","root","");
            
//             String sqldb = "create database student";
//             PreparedStatement ps = con.prepareStatement(sqldb);
//             int s = ps.executeUpdate();
                
//               String sqltable = "create table students(roll int, name varchar(50), marks float)";
//              PreparedStatement psc = con.prepareStatement(sqltable);
//              int t = psc.executeUpdate();
//              System.out.println(t);
            
            String sqlinsert = "insert into students values(?, ?, ?)";
            PreparedStatement psi = con.prepareStatement(sqlinsert);
            psi.setInt(1, 82);
            psi.setString(2, "Sai vamsi");
            psi.setFloat(3, 95.8f);
            int i = psi.executeUpdate();
            System.out.println(i);

            String sqlres = "select * from students";
            PreparedStatement ps = con.prepareStatement(sqlres);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                System.out.println("Student Roll Number : " + rs.getInt(1));
                System.out.println("Student Name : " + rs.getString(2));
                System.out.println("Student Marks : " + rs.getFloat(3));
                System.out.println();
            }


        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
