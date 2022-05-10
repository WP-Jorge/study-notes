package org.example.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

// 测试redis连接
public class TestRedis {
    public static void main( String[] args ) {
        // 1、创建jedis客户端对象
        Jedis jedis = new Jedis("192.168.61.128", 6379);
        // 2、选择一个库 默认使用0号库
        jedis.select(0);
        
        // 获取redis所有key信息
        Set<String> keys = jedis.keys("*");
        keys.forEach(key -> System.out.println("key = " + key));
        
        // 操作库相关
        // jedis.flushDB(); // 清空当前库
        jedis.flushAll(); // 清空所有库
    
        // 3、释放资源
        jedis.close();
    }
}
