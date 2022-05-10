package com.xxxx.client.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoubin
 * @since 1.0.0
 */
@RestController
@RequestMapping("/user")
public class Controller {

	@RequestMapping("/getCurrentUser")
	public Object getCurrentUser(Authentication authentication) {
		return authentication;
	}
}