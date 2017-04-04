package com.steven.mybatis.demo.dao;

import java.util.List;

import com.steven.mybatis.demo.entity.User;

/**
 * 用户数据访问接口
 * 
 * @author liuzhuanghong
 *
 */
public interface IUserDao {
	/*
	 * 获得所有用户数据
	 */
	public List<User> getAllUsers();
}
