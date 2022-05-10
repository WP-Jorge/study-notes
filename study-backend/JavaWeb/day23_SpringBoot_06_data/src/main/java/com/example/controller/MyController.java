package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MyController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/userList")
	public List<Map<String, Object>> userList() {
		String sql = "select * from user";
		List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
		return mapList;
	}
}
