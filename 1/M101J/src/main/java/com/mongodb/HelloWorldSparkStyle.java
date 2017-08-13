package com.mongodb;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by aman.kumar on 24-03-2017.
 */
public class HelloWorldSparkStyle    {
    public static void main(String[] args) {
    	Spark.get(new Route("/") {
			
			@Override
			public Object handle(Request arg0, Response arg1) {
				return "Hello World";
			}
		});
    }
}
