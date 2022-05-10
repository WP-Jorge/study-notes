package spring.boot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 引入lombok使用@Slf4j可以快捷使用日志
@Slf4j
@Controller
@RestController
public class HelloController {
	
	@RequestMapping("/hello")
	public String handle01() {
		log.info("进来了。。。。。。");
		return "Hello, SpringBoot2!";
	}
}
