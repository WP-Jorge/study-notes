package com.example.boxmusic.service;

import com.example.boxmusic.utils.R;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {
	
	String uploadImage(MultipartFile imageFile);
	
	Boolean deleteImage(String imageName);
	
	R uploadFile(MultipartFile file);
}
