package com.example.orderapp.patterns.adapter;

/**
 * Author Admin
 * Create 2021/6/11 12:23
 */
public class Adapter implements AliPay {
	private JiansheCard jiansheCard = new JiansheCard();
	
	@Override
	public String pay(Double price) {
		jiansheCard.pay(price);
		return "使用支付宝适配建设银行卡支付成功！支付金额为：" + price;
	}
}
