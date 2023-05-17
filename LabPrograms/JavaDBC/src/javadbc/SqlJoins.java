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
public class SqlJoins 
{
    public static void main(String[] args)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/project1","root","");
            Statement st = con.createStatement();
            
            String selfjoin = "select * from customer_details t1, customer_details t2 where t2.rating < 10 and t1.cid > 10";
            ResultSet rs = st.executeQuery(selfjoin);
            
            while(rs.next())
            {
                int cid = rs.getInt("cid");
                String cname = rs.getString("cname");
                float rating = rs.getFloat("rating");
                int age = rs.getInt("age");
                
                int cid1 = rs.getInt("cid");
                String cname1 = rs.getString("cname");
                float rating1 = rs.getFloat("rating");
                int age1 = rs.getInt("age");
                
                System.out.println(cid + "\t\t" + cname + "\t\t" + rating + "\t\t" + age + cid1 + "\t\t" + cname1 + "\t\t" + rating1 + "\t\t" + age1);
            }
        }
        catch(Exception e)
        {
            System.out.println("Some Unknown Exception Occered : \n" + e);
        }
    }
}
