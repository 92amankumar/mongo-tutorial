package com.mongodb;

import java.io.StringWriter;
import java.util.Arrays;
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
public class FruitSparkFreemarkerForm {
    public static void main(String[] args) {
        final Configuration conf = new Configuration();
        conf.setClassForTemplateLoading(FruitSparkFreemarkerForm.class,"/");
        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                try {
                    Map<String,Object> map = new HashMap<>();
                    map.put("fruits", Arrays.asList("apple","orange","mango"));
                    Template template = conf.getTemplate("fruit.ftl");
                    StringWriter sw = new StringWriter();
                    template.process(map,sw);
                    return  sw;
                } catch (Exception e) {
                    halt(500);
                    e.printStackTrace();
                    return  null;
                }
            }
        });
        Spark.post(new Route("/fav_fruit") {
            @Override
            public Object handle(Request request, Response response) {
                final String fruit = request.queryParams("fruit");
                if(fruit==null)
                    return "Why don't you pick one?";
                else
                    return "You Picked "+fruit;
            }
        });
    }
}
