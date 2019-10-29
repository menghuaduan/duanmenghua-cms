package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bean.User;
import com.mapper.UserMapper;

/**
 * @作者： 段梦华
 * @时间：2019年10月16日
 */
@Service
public class UserService {
	@Autowired
	UserMapper mapper;

	
	//注册账号
	public int register(User user) {
		
		 
		return mapper.register(user);
		
	}


	//验证账号唯一性
	public User findByUsername(User user) {
		
		return mapper.findByUsername(user);
	}


	//查询所有用户
	public List<User> finduser() {
		// TODO Auto-generated method stub
		return mapper.findUser();
	}


	//改变用户的状态
	public int changeUser(User user) {
		
		return mapper.chageUser(user);
	}

}
