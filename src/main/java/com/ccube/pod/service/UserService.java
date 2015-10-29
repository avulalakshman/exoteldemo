package com.ccube.pod.service;

import java.util.List;

import com.ccube.pod.domain.User;

public interface UserService {
		
			public List<User> listUser();
			public User getUser(long rid);
			long getUserIdByName(String name);
			
}
