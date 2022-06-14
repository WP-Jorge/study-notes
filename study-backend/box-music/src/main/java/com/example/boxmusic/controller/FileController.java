package com.example.boxmusic.controller;

import com.example.boxmusic.service.FileService;
import com.example.boxmusic.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	// @PostMapping("/uploadFile")
	// public R uploadFile(@RequestBody MultipartFile file) {
	// 	return fileService.uploadFile(file);
	// }
}
