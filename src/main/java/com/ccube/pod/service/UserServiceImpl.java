package com.ccube.pod.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccube.pod.domain.Receiver;
import com.ccube.pod.domain.User;
import com.ccube.pod.repository.IdGenerator;
import com.ccube.pod.repository.UserRepository;
import com.ccube.pod.service.exception.ReceiverException;
import com.ccube.pod.service.exception.UserException;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public List<User> listUser() {
		List<User> userList = userRepository.findAll();
		if (userList != null && !userList.isEmpty())
			return userList;
		else {
			LOGGER.warn("Users list is empty or null");
			throw new UserException(UserException.NO_RECORDS_FOUND, userList.size());
		}

	}

	@Override
	public User addUser(User user) {
		try{
			user=userRepository.addUser(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public long getUserIdByName(String name) {
		User user = userRepository.getUserIdByName(name);
		if (user != null) {
			return user.getUid();
		} else {
			LOGGER.warn("User record is not found with the given name :" + name);
			throw new ReceiverException(UserException.RECORD_NOT_FOUND, name);
		}
	}

	@Override
	public User getUser(long uid) {
		User user = userRepository.getUser(uid);
		if (user != null) {
			return user;
		} else {
			LOGGER.warn("User record is not found with the given id :" + uid);
			throw new ReceiverException(UserException.RECORD_NOT_FOUND, uid);
		}
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

}
