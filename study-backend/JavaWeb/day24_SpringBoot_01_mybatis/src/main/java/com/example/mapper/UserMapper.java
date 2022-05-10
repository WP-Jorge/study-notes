package com.example.mapper;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

// 这个注解表示了这是一个mybatis的mapper类
@Mapper
@Repository
public interface UserMapper {
	List<User> queryUser();
	
	User queryUserById(Integer id);
	
	int addUser(User user);
	
	int updateUser(User user);
	
	int deleteUser(Integer id);
}
