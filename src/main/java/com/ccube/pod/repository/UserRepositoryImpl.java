package com.ccube.pod.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccube.pod.domain.User;

@Repository
public class UserRepositoryImpl implements UserRepository {

	List<User> users = new ArrayList<>();

	public UserRepositoryImpl() {

		User user1 = new User(1, "Shalini", "09591695660");
		User user2 = new User(2, "Lakshman Avula", "09206111699");
		User user3 = new User(3, "Shreekantha", "09480907568");
		users.add(user1);
		users.add(user2);
		users.add(user3);
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

}
