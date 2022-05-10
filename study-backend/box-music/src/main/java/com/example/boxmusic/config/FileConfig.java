package com.example.boxmusic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileConfig implements WebMvcConfigurer {
	
	@Value("${imageUploadPath}")
	private String imageUploadPath;
	
	@Value("${imageUploadPathRelative}")
	private String imageUploadPathRelative;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(imageUploadPathRelative)
				.addResourceLocations("file:/" + imageUploadPath);
	}
}
