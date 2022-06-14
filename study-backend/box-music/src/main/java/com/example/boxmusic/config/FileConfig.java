package com.example.boxmusic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileConfig implements WebMvcConfigurer {
	
	@Value("${spiderPath}")
	private String spiderPath;
	@Value("${spiderPathRelative}")
	private String spiderPathRelative;
	
	@Value("${userPicturePathRelative}")
	private String userPicturePathRelative;
	
	@Value("${userPicturePath}")
	private String userPicturePath;
	
	@Value("${musicPicturePathRelative}")
	private String musicPicturePathRelative;
	
	@Value("${musicPicturePath}")
	private String musicPicturePath;
	
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
				.addResourceLocations("file:/" + spiderPath);
		registry.addResourceHandler(userPicturePathRelative)
				.addResourceLocations("file:/" + userPicturePath);
		registry.addResourceHandler(musicPicturePathRelative)
				.addResourceLocations("file:/" + musicPicturePath);
		registry.addResourceHandler(singerPicturePathRelative)
				.addResourceLocations("file:/" + singerPicturePath);
		registry.addResourceHandler(categoryPicturePathRelative)
				.addResourceLocations("file:/" + categoryPicturePath);
		registry.addResourceHandler(playlistPicturePathRelative)
				.addResourceLocations("file:/" + playlistPicturePath);
		registry.addResourceHandler(musicPathRelative)
				.addResourceLocations("file:/" + musicPath);
		registry.addResourceHandler(otherUploadPathRelative)
				.addResourceLocations("file:/" + otherUploadPath);
	}
}
