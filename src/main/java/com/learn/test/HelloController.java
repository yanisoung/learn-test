package com.learn.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bai
 * @date 2021/7/18 19:27
 */
@RestController
public class HelloController {

	@RequestMapping("/hello")
	public String hello () {
		return "hello !";
	}
}
