package com.steven.mybatis.demo.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.steven.mybatis.demo.dao.IUserDao;
import com.steven.mybatis.demo.dao.util.MyBatisUtil;
import com.steven.mybatis.demo.entity.User;

/**
 * 用户数据访问接口实现类
 * 
 * @author liuzhuanghong
 *
 */
public class UserDaoImpl implements IUserDao {

	@Override
	public List<User> getAllUsers() {
		// 获得会话对象
		SqlSession session = MyBatisUtil.getSession();
		try {
			// 通过MyBatis实现接口IUserDAO，返回实例
			// IUserDao userDAO = session.getMapper(IUserDao.class);
			// return userDAO.getAllUsers();

			String statement = "com.steven.mybatis.demo.entity.UserMapping" + ".getAllUsers";
			List<User> users = session.selectList(statement);
			return users;

		} finally {
			session.close();
		}
	}

}
