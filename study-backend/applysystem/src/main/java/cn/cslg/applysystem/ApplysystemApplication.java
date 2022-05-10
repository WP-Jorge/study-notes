package cn.cslg.applysystem;

import cn.cslg.applysystem.utils.RedisUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Set;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("cn.cslg.applysystem.mapper")
public class ApplysystemApplication implements CommandLineRunner {
	
	@Autowired
	RedisUtil redisUtil;
	
	public static void main(String[] args) {
		SpringApplication.run(ApplysystemApplication.class, args);
	}
	
	// 启动项目时清除除了token以外的redis缓存，防止修改mapper.xml时缓存不刷新造成数据不匹配问题
	@Override
	public void run(String... args) throws Exception {
		redisUtil.deleteAllKeys();
	}
}
