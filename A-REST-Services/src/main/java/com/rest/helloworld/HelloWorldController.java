package com.rest.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	MessageSource messageSource; //Reads the different property files based onn the lanuages.
	
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
	
	//internationlization
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized(
			//@RequestHeader(name = "Accept-Language", required = false)Locale locale //Instead of getting Locale everytime - Check line 47
			)
	{										//Accepting language from headers and taking locale requestheader
		//return "Hello World!";
		
		return messageSource.getMessage("good.morning.message", null, "Default Message", LocaleContextHolder.getLocale());
		
		//en - Hellow World
		//nl - Goede Morgen
		//fr = Bonjour //with the help of MESSAGE Bundles 
		//				instead of hard Coding we Can have seperate properties file for seperate languages
	}
}
