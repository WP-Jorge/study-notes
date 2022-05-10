package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class WrapperTest {
	
	@Autowired
	private UserMapper userMapper;
	
	@Test
	void contextLoads() {
		// 查询name不为空的并且邮箱不为空，年龄大于12岁的的用户
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		
		wrapper.isNotNull("name")
				.isNotNull("email")
				.ge("age", 12);
		
		List<User> users = userMapper.selectList(wrapper);
		users.forEach(System.out::println);
	}
	
	@Test
	public void test2() {
		// 查询名字为乔治的
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("name", "乔治");
		userMapper.selectOne(wrapper);
	}
	
	@Test
	public void test3() {
		// 查询年龄在10-20之间的用户
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.between("age", 10, 20);
		userMapper.selectCount(wrapper);
	}
	
	@Test
	public void test4() {
		// 模糊查询
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.notLike("name", "n")
				.likeRight("email", "21")
				.likeLeft("email", "98@qq.com");
		// map形式
		// userMapper.selectMaps(wrapper);
		
		// object形式
		userMapper.selectObjs(wrapper);
	}
	
	@Test
	public void test5() {
		// id 在子查询中查找出来
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.inSql("id", "select id from user where id < 7");
		userMapper.selectObjs(wrapper);
	}
	
	@Test
	public void test6() {
		// 通过id进行排序
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.orderByDesc("id");
		userMapper.selectList(wrapper);
	}
}
