package org.example;

import org.apache.shiro.crypto.hash.Md5Hash;

public class ShiroMD5 {
	public static void main(String[] args) {
		// 使用md5
		Md5Hash md5Hash = new Md5Hash("111111");
		System.out.println(md5Hash.toHex());
		
		// 使用MD5 + salt处理
		Md5Hash md5Hash1 = new Md5Hash("111111", "asd");
		System.out.println(md5Hash1.toHex());
		
		// 使用MD5 + salt + hash散列
		Md5Hash md5Hash2 = new Md5Hash("111111", "asd", 1024);
		System.out.println(md5Hash2.toHex());
	}
}
