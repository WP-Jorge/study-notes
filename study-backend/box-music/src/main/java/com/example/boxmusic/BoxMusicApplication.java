package com.example.boxmusic;

import com.example.boxmusic.utils.RedisUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.example.boxmusic.mapper")
public class BoxMusicApplication implements CommandLineRunner {
	
	@Autowired
	RedisUtil redisUtil;
	
	public static void main(String[] args) {
		SpringApplication.run(BoxMusicApplication.class, args);
	}
	
	// 启动项目时清除除了 token 以外的 redis 缓存，防止修改 mapper.xml 时缓存不刷新造成数据不匹配问题
	@Override
	public void run(String... args) throws Exception {
		redisUtil.deleteAllKeys();
	}
}
