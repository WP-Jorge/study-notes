package com.example.musicdemo;

import com.example.musicdemo.config.AudioConfig;
import com.github.hui.quick.plugin.audio.AudioWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MusicDemoApplicationTests {

    @Test
    void contextLoads() {
        String[] arr = new String[]{
                "D:/下载/aaaa.flac"
        };
        for (String src : arr) {
            try {
                String output = AudioWrapper.of(src)
                        .setInputType("flac")
                        .setOutputType("mp3")
                        .asFile();
                System.out.println(output + "<<<");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
