package com.example.boxmusic.service;

import com.example.boxmusic.utils.R;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {
	
	String uploadFile(String targetPath, MultipartFile file);
	
	Boolean deleteFile(String targetPath, String fileName);
	
	String getFilename(MultipartFile file, Long id);
	
	String uploadFile(MultipartFile file, String targetPath, String filename);
	
	Boolean moveFile(String filename, String from, String to);
	
}
