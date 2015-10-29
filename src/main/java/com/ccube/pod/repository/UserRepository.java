package com.ccube.pod.repository;

import java.util.List;

import com.ccube.pod.domain.User;

public interface UserRepository  {
	
		public List<User> findAll();
		public User getUser(long id);
		public User getUserIdByName(String name);
}
