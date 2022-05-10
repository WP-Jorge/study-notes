package cn.cslg.applysystem;

import cn.cslg.applysystem.utils.Value;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;
import java.util.Set;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisTests {
	
	@Autowired
	RedisTemplate redisTemplate;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Test
	void testDeleteKey() {
		// 最后一定要带上 *
		// String packagePath = "cn.cslg*";
		// Set<String> keys = redisTemplate.keys(packagePath);
		// System.out.println(keys);
		// redisTemplate.delete(keys);
		// String aPackage = String.valueOf(ApplysystemApplication.class.getPackage());
		// String packageFix = aPackage.substring(8);
		// System.out.println(packageFix);
		Map<String, Object> annotatedBeans = applicationContext.getBeansWithAnnotation(SpringBootApplication.class);
		String name = String.valueOf(annotatedBeans.values().toArray()[0].getClass().getPackage());
		String srcPath = name.substring(Value.PACKAGE_PREFIX_LENGTH);
		Set<String> keys = redisTemplate.keys(srcPath + "*");
		System.out.println(keys);
		redisTemplate.delete(keys);
	}
}
