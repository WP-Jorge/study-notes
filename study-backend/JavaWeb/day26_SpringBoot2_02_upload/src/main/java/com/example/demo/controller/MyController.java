package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
public class MyController {
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	/**
	 * MultipartFile 自动封装上传过来的文件
	 */
	@PostMapping("/upload")
	public String upload(@RequestParam("username") String username,
	                     // 单个文件使用@RequestPart("headerImg") MultipartFile headerImg
	                     @RequestPart("headerImg") MultipartFile headerImg,
	                     // 多个文件使用@RequestPart("photos") MultipartFile[] photos
	                     @RequestPart("photos") MultipartFile[] photos) {
		log.info("上传的信息：username={}, headerImg={}, photos={}",
				username, headerImg.getSize(), photos.length);
		
		if (!headerImg.isEmpty()) {
			try {
				// 保存到服务器或者自己的磁盘,我们存放在static\header里面
				String originalFilename = headerImg.getOriginalFilename();
				headerImg.transferTo(new File("D:\\developTools\\allProject\\JavaWeb\\day26_SpringBoot2_02_upload\\src\\main\\resources\\static\\header\\" + originalFilename));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (photos.length > 0) {
			for (MultipartFile photo : photos) {
				if (!photo.isEmpty()) {
					try {
						String originalFilename = photo.getOriginalFilename();
						photo.transferTo(new File("D:\\developTools\\allProject\\JavaWeb\\day26_SpringBoot2_02_upload\\src\\main\\resources\\static\\photos\\" + originalFilename));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		return "index";
	}
}
