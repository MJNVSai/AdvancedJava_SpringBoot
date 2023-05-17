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
public class ProductAccess 
{
    public static void main(String[] args)
    {
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); 
           
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://E:\\venkat sai\\WebDev_Program\\Products_Database.accdb");
            Statement st = con.createStatement();
            
            // Select Query
            ResultSet rs = st.executeQuery("select * from Products");
            while(rs.next())
            {

                System.out.println("Serial Number is : " + rs.getInt(2));
                System.out.println("Product Number : " + rs.getInt(3));
                System.out.println("Product Name : " + rs.getString(4));
                System.out.println("Product price : " + rs.getInt(5));
                System.out.println("Product Quantity : " + rs.getInt(6));
                System.out.println();
            }
        }
        catch(Exception e)
        {
            System.out.println("An Unknown Error Occured : \n" + e);
        }
    }
}
