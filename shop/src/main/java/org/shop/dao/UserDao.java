package org.shop.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.shop.pojo.User;

public interface UserDao {
	User login(User user);//登陆和验证
	int touser(User user);//用户注册
	List findall(User user);//查找全部用户
	int delete(int id);//根据id删除用户
	User findid(int id);//根据id查找用户
	int  userup(User user);//用户更新

	
	
	
	
}
