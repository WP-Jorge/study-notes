package com.example.nacosclient8789_1.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {
	
	@GetMapping("/test/test")
	@SentinelResource(value = "aa", fallback = "testFailBack", blockHandler = "testBlockHandler")
	public String test(Integer id) {
		log.info("进入test服务");
		if (id < 0) {
			throw new RuntimeException("id非法！！！");
		}
		return "test服务调用成功";
	}
	
	@GetMapping("/test/test1")
	public String test1() {
		log.info("进入test1服务");
		return "test1服务调用成功";
	}
	
	// 处理降级、流控异常自定义异常信息方法
	public String testBlockHandler(Integer id, BlockException blockException) {
		if (blockException instanceof FlowException) {
			return "活动异常火爆，当前请求被限流" + blockException.getClass().getSimpleName();
		}
		if (blockException instanceof DegradeException) {
			return "当前服务已被降级处理，请稍后再试" + blockException.getClass().getSimpleName();
		}
		return "当前服务不可达";
	}
	
	public String testFailBack(Integer id) {
		return id + ": 为非法参数,请检查后再次尝试";
		
	}
}
