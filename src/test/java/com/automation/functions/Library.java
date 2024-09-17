package com.automation.functions;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.Map;

public class Library {

    public static String createPayLoad(String template, Map<String,String> data){

        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();
        VelocityContext context =  new VelocityContext();
        StringWriter writer = new StringWriter();
        Template template1;
        template1 = velocityEngine.getTemplate(template);
        data.forEach((k,v)->context.put(k,v));
        template1.merge(context,writer);
        return writer.toString();


    }
}
