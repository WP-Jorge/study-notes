package com.example.shirojwt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
	private Integer roleId;
	private String roleName;
	
	// 定义权限集合
	private List<Perms> perms;
}
