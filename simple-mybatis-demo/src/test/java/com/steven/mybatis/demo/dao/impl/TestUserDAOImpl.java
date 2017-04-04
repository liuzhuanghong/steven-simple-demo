package com.steven.mybatis.demo.dao.impl;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.steven.mybatis.demo.dao.IUserDao;
import com.steven.mybatis.demo.entity.User;

public class TestUserDAOImpl {

	@Test
	public void testGetAllUsers() throws IOException {
		IUserDao userDao = new UserDaoImpl();
		List<User> users = userDao.getAllUsers();
		for (User eachUser : users) {
			System.out.println(eachUser);
		}
	}

	@Test
	public void testGetUser() throws IOException {
		String resource = "MyBatisCfg.xml";
		// 加载 mybatis 的配置文件(它也加载关联的映射文件)
		Reader reader = Resources.getResourceAsReader(resource);
		// 构建 sqlSession 的工厂
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		// 创建能执行映射文件中 sql 的 sqlSession
		SqlSession session = sessionFactory.openSession();
		// 映射 sql 的标识字符串
		String statement = "com.steven.mybatis.demo.entity.UserMapping" + ".selectUser";
		// 执行查询返回一个唯一 user 对象的 sql
		User user = session.selectOne(statement, 1);
		System.out.println(user);
	}
}
