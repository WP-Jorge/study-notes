package com.example.boxmusic.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.example.boxmusic.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadFile(String targetPath, MultipartFile file) {
		targetPath = getTargetPath(targetPath);
		String filename = getFilename(file);
		File filepath = new File(targetPath, filename);
		if (!filepath.getParentFile().exists()) {
			filepath.getParentFile().mkdirs();
		}
		try {
			file.transferTo(new File(targetPath + filename));
			return filename;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Boolean deleteFile(String targetPath, String fileName) {
		targetPath = getTargetPath(targetPath);
		File filepath = new File(targetPath, fileName);
		return filepath.delete();
	}
	
	private String getFilename(MultipartFile file) {
		String filename = file.getOriginalFilename();
		int lastIndex = filename.lastIndexOf(".");
		String name = filename.substring(0, lastIndex);
		String[] split = filename.split("\\.");
		name += "-" + RandomUtil.randomString(6);
		filename = name + '.' + split[split.length - 1];
		return filename;
	}
	
	@Override
	public String getFilename(MultipartFile file, Long id) {
		String filename = file.getOriginalFilename().replaceAll(" ", "");
		int lastIndex = filename.lastIndexOf(".");
		String name = filename.substring(0, lastIndex);
		String[] split = filename.split("\\.");
		name += "-" + id;
		filename = name + '.' + split[split.length - 1];
		return filename;
	}
	
	@Override
	public String uploadFile(MultipartFile file, String targetPath, String filename) {
		targetPath = getTargetPath(targetPath);
		File filepath = new File(targetPath, filename);
		if (!filepath.getParentFile().exists()) {
			filepath.getParentFile().mkdirs();
		}
		try {
			file.transferTo(filepath);
			return filename;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Boolean moveFile(String filename, String from, String to) {
		File startFile = new File(from, filename);
		
		File endDirection = new File(to);
		if (!endDirection.exists()) {
			endDirection.mkdirs();
		}
		
		File endFile = new File(endDirection, filename);
		
		try {
			if (startFile.renameTo(endFile)) {
				System.out.println("文件移动成功！目标路径：{" + endFile.getAbsolutePath() + "}");
				return true;
			} else {
				System.out.println("文件移动失败！起始路径：{" + startFile.getAbsolutePath() + "}");
				return false;
			}
		} catch (Exception e) {
			System.out.println("文件移动出现异常！起始路径：{" + startFile.getAbsolutePath() + "}");
			return false;
		}
	}
	
	private String getTargetPath(String targetPath) {
		return targetPath.endsWith("/") ? targetPath : targetPath + "/";
	}
}
