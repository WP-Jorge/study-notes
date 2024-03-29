package com.example.demo.utils;

import java.util.Random;

/**
 * 生成salt的静态方法
 */
public class SaltUtil {
	public static String getSalt(int n) {
		char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!@#$%^&*()_+".toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			char aChar = chars[new Random().nextInt(chars.length)];
			sb.append(aChar);
		}
		return sb.toString();
	}
}
