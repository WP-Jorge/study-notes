package com.example.boxmusic.utils;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.concurrent.TimeUnit;

@Component
public class VerificationCodeUtil {
	
	@Autowired
	private RedisUtil redisUtil;
	
	public String getVerificationCode(String randomCode) {
		// 定义图形验证码的长、宽、验证码字符数、干扰元素个数
		ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(90, 34, 4, 3);
		// 设置背景颜色
		captcha.setBackground(Color.white);
		// 验证图形验证码的有效性，返回 boolean 值
		// captcha.verify();
		// 放入 redis
		redisUtil.pushVerificationCode("code_" + randomCode, captcha.getCode(), Value.VERIFICATION_CODE_EXPIRATION_TIME, TimeUnit.DAYS);
		return captcha.getImageBase64Data();
	}
	
	public Boolean verifyVerificationCode(String codeId, String verificationCode) {
		String verificationCode1 = redisUtil.getVerificationCode("code_" + codeId);
		return verificationCode.equals(verificationCode1);
	}
}
