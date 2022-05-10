package com.example.shiro_springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Perms {
	private Integer permsId;
	private String permsName;
	private String url;
}
