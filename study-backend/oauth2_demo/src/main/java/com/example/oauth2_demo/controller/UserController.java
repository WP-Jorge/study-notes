package com.example.oauth2_demo.controller;


import com.example.oauth2_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author demo_team
 * @since 2021-03-06
 */
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
}

