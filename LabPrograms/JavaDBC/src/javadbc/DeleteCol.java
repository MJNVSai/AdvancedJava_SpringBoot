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
public class DeleteCol 
{
    public static void main(String[] args)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/it","root","");
            Statement st = con.createStatement();
            
            String table = "create table person(personid int, lastname varchar(50), firstname varchar(50), address varchar(50), city varchar(50))";
            //st.executeUpdate(table);
            
            String insert = "insert into person values(23, 'MJNV', 'Sai', 'BPM', 'VIJ')";
            //st.executeUpdate(insert);
            
            String deletecol = "alter table person drop column address";
            //st.executeUpdate(deletecol);
            
            ResultSet rs = st.executeQuery("select * from person");
            ResultSetMetaData metaData = rs.getMetaData();
            
            System.out.println("Number of columns: " + metaData.getColumnCount());
            System.out.println("Table description...!");
            
            for(int i=1; i< metaData.getColumnCount(); i++)
            {
              System.out.println(metaData.getColumnName(i)+"\t"+metaData.getColumnTypeName(i));
            }

        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println("Some Unknown Error Occured : \n" + e);
        }
    }
}
