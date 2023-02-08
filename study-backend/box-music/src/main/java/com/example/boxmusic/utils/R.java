package com.example.boxmusic.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public R() {
		put("type", ResponseValue.SUCCESS).put("msg", "");
	}
	
	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
	
	public static R info(String msg) {
		R r = new R();
		r.put("type", ResponseValue.INFO);
		r.put("msg", msg);
		return r;
	}
	
	public static R info(HashMap<String, Object> map) {
		R r = new R();
		r.put("type", ResponseValue.INFO).putAll(map);
		return r;
	}
	
	public static R info(String msg, Map<String, Object> map) {
		R r = new R();
		r.put("type", ResponseValue.INFO);
		r.put("msg", msg);
		r.putAll(map);
		return r;
	}
	
	public static R success(String msg) {
		R r = new R();
		r.put("type", ResponseValue.SUCCESS);
		r.put("msg", msg);
		return r;
	}
	
	public static R success(HashMap<String, Object> map) {
		R r = new R();
		r.put("type", ResponseValue.SUCCESS)
				.putAll(map);
		return r;
	}
	
	public static R success(String msg, Map<String, Object> map) {
		R r = new R();
		r.put("type", ResponseValue.SUCCESS);
		r.put("msg", msg);
		r.putAll(map);
		return r;
	}
	
	public static <T> R success(String key, List<T> listValue) {
		HashMap<String, Object> map = new HashMap<>();
		map.put(key, listValue);
		R r = new R();
		r.put("type", ResponseValue.SUCCESS)
				.putAll(map);
		return r;
	}
	
	public static R successPage(String msg, IPage pageData) {
		R r = new R();
		r.put("type", ResponseValue.SUCCESS)
				.put("msg", msg)
				.put("pageList", pageData.getRecords())
				.put("total", pageData.getTotal())
				.put("pageSize", pageData.getSize())
				.put("currentPage", pageData.getCurrent())
				.put("totalPages", pageData.getPages());
		return r;
	}
	
	public static R warning(String msg) {
		R r = new R();
		r.put("type", ResponseValue.WARNING);
		r.put("msg", msg);
		return r;
	}
	
	public static R warning(HashMap<String, Object> map) {
		R r = new R();
		r.put("type", ResponseValue.WARNING)
				.putAll(map);
		return r;
	}
	
	public static R warning(String msg, Map<String, Object> map) {
		R r = new R();
		r.put("type", ResponseValue.WARNING);
		r.putAll(map);
		return r;
	}
	
	public static R error(String msg) {
		R r = new R();
		r.put("type", ResponseValue.ERROR);
		r.put("msg", msg);
		return r;
	}
	
	public static R error(HashMap<String, Object> map) {
		R r = new R();
		r.put("type", ResponseValue.ERROR).putAll(map);
		return r;
	}
	
	public static R error(String msg, Map<String, Object> map) {
		R r = new R();
		r.put("type", ResponseValue.ERROR);
		r.put("msg", msg);
		r.putAll(map);
		return r;
	}
}
