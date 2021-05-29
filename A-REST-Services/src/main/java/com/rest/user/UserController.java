package com.rest.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	private UserDAO userDAO;
	
	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers()
	{
		return userDAO.findAll();
	}
	
	@GetMapping(path = "/users/{id}")
	public User retrieveUser(@PathVariable Integer id)
	{
		return userDAO.findOne(id);
	}
	
	@PostMapping(path ="/users")
	public void saveUser(@RequestBody User user) {
		userDAO.save(user);
	}
}