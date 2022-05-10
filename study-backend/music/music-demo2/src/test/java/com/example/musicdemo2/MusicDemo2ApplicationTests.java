package com.example.musicdemo2;

import com.example.musicdemo2.utils.MyAudioWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MusicDemo2ApplicationTests {

    @Test
    void contextLoads() {
        String[] arr = new String[]{
                "D:/下载/aaaa.flac"
        };
        for (String src : arr) {
            try {
                String output = MyAudioWrapper.of(src)
                        .setInputType("flac")
                        .setOutputType("mp3")
                        .asFile();
                System.out.println(output);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
