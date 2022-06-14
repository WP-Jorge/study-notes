package com.example.boxmusic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.boxmusic.utils.FfmpegUtil;
import com.example.boxmusic.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
class BoxMusicApplicationTests {
	
	@Autowired
	private FfmpegUtil ffmpegUtil;
	
	
	@Test
	void contextLoads() {
		String res = ffmpegUtil.transformAudio("1", "红昭愿-452986458.flac", "1", "红昭愿-452986458.mp3", "128k");
		System.out.println(res);
	}
	
	// @Test
	// void testReadMusic() {
	// 	Map musicInfo = ffmpegUtil.getMusicInfo("C:\\Users\\Admin\\Desktop/故梦.mp3");
	// 	System.out.println(musicInfo);
	// }
	
	// @Test
	// void testUidGenerator() {
	// 	System.out.println(cachedUidGenerator.getUID());
	// 	System.out.println(cachedUidGenerator.getUID());
	// }
}
