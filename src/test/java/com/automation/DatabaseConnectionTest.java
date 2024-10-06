package com.automation;

import io.cucumber.java.it.Ma;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DatabaseConnectionTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //Class.forName("org.prostgres.Driver").newInstance();
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/training", "vusi", "password");

        Statement statement = connection.createStatement();

        String strQuery = "SELECT * FROM public.products\n" +
                "ORDER BY product_id ASC LIMIT 100";
        ResultSet resultSet = statement.executeQuery(strQuery);

        Map<String,String> map = new HashMap<>();

        while (resultSet.next()){

            map.put(resultSet.getString(1),resultSet.getString(5));
        }

        map.forEach((k,v)-> System.out.println("Key : "+k +" - "+ "Key : " +v));



        connection.close();



    }
}
