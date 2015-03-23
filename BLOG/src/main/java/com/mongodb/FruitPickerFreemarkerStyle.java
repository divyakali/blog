package com.mongodb;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class FruitPickerFreemarkerStyle {

	public static void main(String[] args) {
		final Configuration configuration = new Configuration();
		configuration.setClassForTemplateLoading(FruitPickerFreemarkerStyle.class, "/");
		///favourite_fruit
		Spark.get(new Route("/"){
			@Override
			public Object handle(Request arg0, Response arg1) {
				StringWriter writer = new StringWriter();
				try{
				Template fruitTemplate = configuration.getTemplate("fruit.ftl");
				Map<String,Object> fruitPickerMap = new HashMap<String,Object>();
				fruitPickerMap.put("fruits", Arrays.asList("mango","apple","banana"));
				
				fruitTemplate.process(fruitPickerMap,writer);
				System.out.println(writer);
				}
				catch(Exception ex){
					halt(500);
					ex.printStackTrace();
				}
				return writer;
			}
			
		});
		
		Spark.post(new Route("/favourite_fruit"){
			@Override
			public Object handle(Request req, Response res) {
				String fruit = req.queryParams("fruit");
				if(fruit==null)
					return "Why don't you pick a fruit";
				else
					return "Your favourite fruit is "+fruit;
				}
			
		});
	}

}
