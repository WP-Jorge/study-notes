package com.example.boxmusic.service.impl;

import com.example.boxmusic.service.FileService;
import com.example.boxmusic.utils.R;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

@Service
public class FIleServiceImpl implements FileService {
	
	@Value("${imageUploadPath}")
	private String imageUploadPath;
	
	@Override
	public String uploadImage(MultipartFile imageFile) {
		String filename = imageFile.getOriginalFilename();
		String[] split = filename.split("\\.");
		split[0] += RandomStringUtils.random(6, true, true);
		filename = String.join(".", split);
		String savePath = imageUploadPath;
		File filepath = new File(savePath, filename);
		if (!filepath.getParentFile().exists()) {
			filepath.getParentFile().mkdir();
		}
		try {
			imageFile.transferTo(new File(savePath + filename));
			return "/images/" + filename;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Boolean deleteImage(String imageName) {
		String newImagePath = imageName.replace("/images/", "");
		File filepath = new File(imageUploadPath, newImagePath);
		return filepath.delete();
	}
	
	@Override
	public R uploadFile(MultipartFile file) {
		String filename = file.getOriginalFilename();
		String savePath = imageUploadPath;
		File filepath = new File(savePath, filename);
		if (!filepath.getParentFile().exists()) {
			filepath.getParentFile().mkdir();
		}
		try {
			file.transferTo(new File(savePath + filename));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return R.success("上传文件成功").put("data", "/images/" + filename);
	}
}
