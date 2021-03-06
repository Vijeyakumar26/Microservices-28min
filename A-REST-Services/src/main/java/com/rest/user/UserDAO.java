package com.rest.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDAO {
	
	private static ArrayList<User> userlist = new ArrayList<>();
	
	int usersCount = 3;
	
	static {
		userlist.add(new User(1,"Jonas",new Date()));
		userlist.add(new User(2,"Marth",new Date()));
		userlist.add(new User(3,"Mikkel",new Date()));
	}
	
	public List<User> findAll() {
		return userlist;
	}

	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++usersCount);
		}
		userlist.add(user);
		return user;
	}

	public User findOne(int id) {
		for (User user : userlist) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteById(int id) {
		Iterator<User> iterator = userlist.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}
