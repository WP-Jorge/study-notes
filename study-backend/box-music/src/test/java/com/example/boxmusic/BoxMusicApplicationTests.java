package com.example.boxmusic;

import com.example.boxmusic.utils.FfmpegUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoxMusicApplicationTests {
	
	@Test
	void contextLoads() {
		String res = FfmpegUtil.transformAudio("1", "红昭愿-452986458.flac", "1", "红昭愿-452986458.mp3", "128k");
		System.out.println(res);
	}
	
}
