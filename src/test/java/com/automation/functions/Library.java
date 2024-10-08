package com.automation.functions;

import com.github.javafaker.Faker;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Random;

public class Library {

    public static String createPayLoad(String template, Map<String,String> data){

        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();
        VelocityContext context =  new VelocityContext();
        StringWriter writer = new StringWriter();
        Template template1;
        Faker faker = new Faker();
        template1 = velocityEngine.getTemplate(template);
        data.forEach((k,v)->{
            if(v.equals("-")){
                switch (k.toString()){
                    case "totalprice":
                        context.put("totalprice", faker.number().randomNumber());
                    break;
                              //  "n I enter the following data                                                                          \n" +
                                //" firstname   | lastname   | totalprice   | depositpaid   | checkin   | checkout   | additionalneeds   ")

                }

            }else{
                context.put(k,v);
            }


        });
        template1.merge(context,writer);
        return writer.toString();


    }

    public static Connection getDatabaseConnection(String Host, String DBName, String Username, String Password  ) throws SQLException {

        return  DriverManager.getConnection("jdbc:postgresql://"+Host+":5432/"+DBName ,Username, Password);

    }
}
