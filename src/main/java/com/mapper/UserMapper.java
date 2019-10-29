package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.bean.User;

/**
 * @作者： 段梦华
 * @时间：2019年10月16日
 */
public interface UserMapper {

	//注册账号
	int register(User user);

	//根据用户名查找用户
	User findByUsername(User user);
	
	 //根据用户名查找用户
	User findByName(@Param("username")String username);

	//查询所有用户
	List<User> findUser();

	//改变用户状态
	int chageUser(User user);

}
