package com.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rest.bean.HelloWorldBean;

@RestController
public class HelloWorldController {
	
	//adding URI and returing String
	@GetMapping(path = "/hello-world")
	public String helloWorld()
	{
		return "Hello World!";
	}
	
	//returing a bean 
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean()
	{
		return new HelloWorldBean("Hello World");
	}
	
	//adding a path variable and displaying it.
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldBean(@PathVariable String name)
	{
		return new HelloWorldBean(String.format("Hello World ,%s" , name));
	}
}
