package com.rest.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaticFilterController {
	
	@GetMapping("/filtering")
	public SomeBean retrieveSomeBean()
	{
		return new SomeBean("field1", "field2", "field3");
	}
	
	@GetMapping("/filtering-list")
	public List<SomeBean> retrieveListOfSomeBean()
	{
		return Arrays.asList(new SomeBean("field1", "field2", "field3"),new SomeBean("field11", "field21", "field31"));
	}
}
