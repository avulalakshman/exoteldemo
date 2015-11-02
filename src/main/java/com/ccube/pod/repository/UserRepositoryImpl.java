package com.ccube.pod.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccube.pod.domain.Receiver;
import com.ccube.pod.domain.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	List<User> users = new ArrayList<>();

	public UserRepositoryImpl() {

		User user1 = new User(1, "Shalini", "+919900122277");
		User user2 = new User(2, "Venkatesh", "+919880366899");
		
		users.add(user1);
		users.add(user2);
	}

	@Override
	public List<User> findAll() {
		return users;
	}

	@Override
	public User getUser(long id) {
		for (User user : users) {
			if (user.getUid() == id)
				return user;
		}

		return null;
	}

	@Override
	public User getUserIdByName(String name) {
		for (User user : users) {
			if (user.getFullName().equals(name.trim()))
				return user;
		}

		return null;
	}

	@Override
	public User addUser(User user) {
		if (user != null) {
			user.setUid(IdGenerator.getUid());
		}
		boolean isAdded =users.add(user);
		if (isAdded) {
			return user;
		}else{
			throw new IllegalArgumentException("Unable to add the User:"+user);
		}
	}

	@Override
	public void deleteUser(long uid) {
		users.remove(getIndex(uid));
	}
	
	private int getIndex(long uid){
		int i=-1;
		int count=0;
		for (User receiver : users) {
			if (receiver.getUid() == uid){
				i=count;
				return i;
			}
			count++;
		}
		return i;
	}

}
