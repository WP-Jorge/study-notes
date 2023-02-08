package com.example.boxmusic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileConfig implements WebMvcConfigurer {

	@Value("${basePath}")
	private String basePath;

	@Value("${spiderPath}")
	private String spiderPath;

	@Value("${spiderPathRelative}")
	private String spiderPathRelative;
	
	@Value("${userPicturePathRelative}")
	private String userPicturePathRelative;
	
	@Value("${userPicturePath}")
	private String userPicturePath;
	
	@Value("${albumPicturePathRelative}")
	private String albumPicturePathRelative;
	
	@Value("${albumPicturePath}")
	private String albumPicturePath;
	
	@Value("${singerPicturePathRelative}")
	private String singerPicturePathRelative;
	
	@Value("${singerPicturePath}")
	private String singerPicturePath;
	
	@Value("${categoryPicturePath}")
	private String categoryPicturePath;
	
	@Value("${playlistPicturePathRelative}")
	private String playlistPicturePathRelative;
	
	@Value("${playlistPicturePath}")
	private String playlistPicturePath;
	
	@Value("${categoryPicturePathRelative}")
	private String categoryPicturePathRelative;
	
	@Value("${musicPathRelative}")
	private String musicPathRelative;
	
	@Value("${musicPath}")
	private String musicPath;
	
	@Value("${otherUploadPathRelative}")
	private String otherUploadPathRelative;
	
	@Value("${otherUploadPath}")
	private String otherUploadPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(spiderPathRelative)
				.addResourceLocations("file:/" + basePath + spiderPath);
		registry.addResourceHandler(userPicturePathRelative)
				.addResourceLocations("file:/" + basePath + userPicturePath);
		registry.addResourceHandler(albumPicturePathRelative)
				.addResourceLocations("file:/" + basePath + albumPicturePath);
		registry.addResourceHandler(singerPicturePathRelative)
				.addResourceLocations("file:/" + basePath + singerPicturePath);
		registry.addResourceHandler(categoryPicturePathRelative)
				.addResourceLocations("file:/" + basePath + categoryPicturePath);
		registry.addResourceHandler(playlistPicturePathRelative)
				.addResourceLocations("file:/" + basePath + playlistPicturePath);
		registry.addResourceHandler(musicPathRelative)
				.addResourceLocations("file:/" + basePath + musicPath);
		registry.addResourceHandler(otherUploadPathRelative)
				.addResourceLocations("file:/" + basePath + otherUploadPath);
	}
}
