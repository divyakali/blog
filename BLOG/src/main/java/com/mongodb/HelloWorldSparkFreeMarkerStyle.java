package com.mongodb;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class HelloWorldSparkFreeMarkerStyle {
	public static void main(String[] args) {
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(HelloWorldSparkFreeMarkerStyle.class, "/");
		
		Spark.get(new Route("/"){
			@Override
			public Object handle(Request arg0, Response arg1) {
				StringWriter writer = new StringWriter();
				try{
				Template helloTemplate = configuration.getTemplate("hello.ftl");
				Map<String,Object> helloMap = new HashMap<String,Object>();
				helloMap.put("name", "Freemarker");
				
				helloTemplate.process(helloMap,writer);
				System.out.println(writer);
				}
				catch(Exception ex){
					halt(500);
					ex.printStackTrace();
				}
				return writer;
			}
			
		});
	}
}
