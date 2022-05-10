package com.example.demo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.UserMapper;
import com.example.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Slf4j
@SpringBootTest
class Day29MyBatisPlus01StartApplicationTests {

	@Autowired
	private UserMapper userMapper;
	
	@Test
	void contextLoads() {
		// 查询全部用户
		// 参数是一个Wrapper条件构造器,不用的话就写null
		List<User> userList = userMapper.selectList(null);
		System.out.println(userList);
	}
	
	// 测试插入
	@Test
	public void testInsert() {
		User user = new User("乔治", 18, "85@qq.com"); // 帮我们自动生成id
		int i = userMapper.insert(user);
		System.out.println(i);
	}
	
	// 测试更新
	@Test
	public void testUpdate() {
		User user = new User(6L, "野猪乔治", 21, "8535@qq.com");
		int i = userMapper.updateById(user); // 参数是个对象
		System.out.println(i);
	}
	
	// 测试乐观锁成功案例
	@Test
	public void testOptimisticLocker() {
		// 1.查询用户信息
		User user = userMapper.selectById(5L);
		// 2.修改用户信息
		user.setName("乔治");
		user.setEmail("1238129@qq.com");
		// 3.执行更新操作
		userMapper.updateById(user);
	}
	
	// 测试乐观锁失败案例，多线程下
	@Test
	public void testOptimisticLocker2() {
		// 线程1
		User user = userMapper.selectById(5L);
		user.setName("乔治111");
		user.setEmail("1238129@qq.com");
		
		// 模拟另一个线程插队操作
		// 3.执行更新操作
		User user2 = userMapper.selectById(5L);
		user2.setName("乔治222");
		user2.setEmail("1238129@qq.com");
		userMapper.updateById(user2);
		
		// 可以使用自旋锁进行多次尝试提交
		userMapper.updateById(user); // 如果没有乐观锁就会覆盖插队的线程的值
	}
	
	// 测试查询
	// 当个
	@Test
	public void testSelectById() {
		User user = userMapper.selectById(1L);
		System.out.println(user);
	}
	
	// 多个
	@Test
	public void testSelectByBatchId() {
		List<User> users = userMapper.selectBatchIds(Arrays.asList(1l, 2l, 3l));
		System.out.println(users);
	}
	
	// 按条件查询使用map
	@Test
	public void testSelectByMap() {
		HashMap<String, Object> map = new HashMap<>();
		// 自定义查询
		map.put("name", "乔治222");
		map.put("age", 18);
		
		userMapper.selectByMap(map);
	}
	
	// 测试分页查询
	@Test
	public void testPage() {
		// 参数一：当前页，参数二：页面大小
		Page<User> page = new Page<>(2,4);
		userMapper.selectPage(page, null);
		page.getRecords().forEach(System.out::println);
		System.out.println(page.getTotal());
	}
	
	// 测试删除
	@Test
	public void testDeleteById() {
		userMapper.deleteById(1l);
	}
	
	// 测试批量删除, 通过id批量删除
	@Test
	public void testDeleteByBatchId() {
		userMapper.deleteBatchIds(Arrays.asList(4L, 3L));
	}
	
	// 通过map删除
	@Test
	public void testDeleteByMap() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", 2l);
		userMapper.deleteByMap(map);
	}

}
