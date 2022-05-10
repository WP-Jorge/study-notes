package com.example.oauth2_demo.utils;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public R() {}
	
	public static R error() {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "服务器异常");
	}
	
	public static R error(String msg) {
		return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
	}
	
	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}
	
	public static R notFound(String msg) {
		return new R().put("code", HttpStatus.SC_NOT_FOUND).put("msg", msg);
	}
	
	public static R notFound() {
		return new R().put("code", HttpStatus.SC_NOT_FOUND).put("msg", "未知请求");
	}
	
	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.put("code", HttpStatus.SC_OK).putAll(map);
		return r;
	}
	
	public static R ok(String msg) {
		return new R().put("code", HttpStatus.SC_OK).put("msg", msg);
	}
	
	
	public static R ok() {
		return new R().put("code", HttpStatus.SC_OK).put("msg", "success");
	}
	
	public static R unauthorized(String msg) {
		return new R().put("code", HttpStatus.SC_UNAUTHORIZED).put("msg", msg);
	}
	
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
