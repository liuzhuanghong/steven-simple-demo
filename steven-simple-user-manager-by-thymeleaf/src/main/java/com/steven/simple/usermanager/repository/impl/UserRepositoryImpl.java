package com.steven.simple.usermanager.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.steven.simple.usermanager.domain.User;
import com.steven.simple.usermanager.repository.UserRepository;

/**
 * 用户资源库
 * 
 * @author Steven
 *
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

	private static AtomicLong counter = new AtomicLong();

	private final ConcurrentMap<Long, User> userMap = new ConcurrentHashMap<Long, User>();

	public UserRepositoryImpl() {
		User user = new User();
		user.setAge(18);
		user.setName("Liu Bei");
		this.saveOrUpateUser(user);
	}

	@Override
	public User saveOrUpateUser(User user) {
		Long id = user.getId();
		if (id <= 0) {
			id = counter.incrementAndGet();
			user.setId(id);
		}
		this.userMap.put(id, user);
		return user;
	}

	@Override
	public void deleteUser(Long id) {
		this.userMap.remove(id);
	}

	@Override
	public User getUserById(Long id) {
		return this.userMap.get(id);
	}

	@Override
	public List<User> listUser() {
		return new ArrayList<User>(this.userMap.values());
	}

}
