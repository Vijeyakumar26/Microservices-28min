package com.rest.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJPAController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping(path = "jpa/users")
	public List<User> retrieveAllUsers()
	{
		return userRepository.findAll();
	}
	
	@GetMapping(path = "jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) throws UserNotFoundException //Implementing HATEOAS by 
																							//returning EntityModel instead of Entity
	{
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent())
			throw new UserNotFoundException("id-"+id);
		
		EntityModel<User> model = EntityModel.of(user.get());
		
		WebMvcLinkBuilder linkTo = 	
				linkTo(methodOn(this.getClass()).retrieveAllUsers());//providing the link of retrieveAllUsers with the Response.
		
		model.add(linkTo.withRel("all-users"));
		
		return model;
	}
	
	@PostMapping("jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {//Valid annotation to check attributes specified in entity
		User savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();	
	}
	
	@DeleteMapping(path = "jpa/users/{id}")
	public void deleteUser(@PathVariable int id) throws UserNotFoundException{
		userRepository.deleteById(id);		
	}
	
	@GetMapping(path = "jpa/users/{id}/posts")			//retrieves list of posts by user
	public List<Post> retrieveUserPosts(@PathVariable int id) throws UserNotFoundException
	{
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent())
			throw new UserNotFoundException("id-"+id);
		
		return userOptional.get().getPosts();
		
	}
	
	@PostMapping("jpa/users/{id}/posts")
	public ResponseEntity<Object> createPosts(@PathVariable int id , @RequestBody Post post) throws UserNotFoundException{
		
		//creating post for a specific user

		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent())
			throw new UserNotFoundException("id-"+id);
		
		User user = userOptional.get(); //getting user from userOptional
		
		post.setUser(user);
		
		postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(location).build();	
	}
}
