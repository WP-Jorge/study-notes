package com.example.orderapp.patterns.adapter;

import org.springframework.stereotype.Component;

/**
 * Author Admin
 * Create 2021/6/11 12:16
 */
public interface AliPay {
	String pay(Double price);
}
