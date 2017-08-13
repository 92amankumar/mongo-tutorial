package com.mongodb;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;


/**
 * Created by aman.kumar on 24-03-2017.
 */
public class HelloWorldSparkFreemarkerStyle {
    public static void main(String[] args) {
        final Configuration conf = new Configuration();
        conf.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class,"/");
        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                try {
                    Template helloTemplate = conf.getTemplate("hello.ftl");
                    Map<String,Object> helloMap = new HashMap<>();
                    helloMap.put("name","Freemarker");
                    StringWriter writer = new StringWriter();
                    helloTemplate.process(helloMap,writer);
                    System.out.println(writer);
                    return  writer;
                } catch (Exception e) {
                    halt(500);
                    e.printStackTrace();
                }
                return null;
            }
        });
    }
}
