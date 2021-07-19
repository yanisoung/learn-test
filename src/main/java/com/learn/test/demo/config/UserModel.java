package com.learn.test.demo.config;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.ToString;

/**
 * @author Bai
 * @date 2021/7/19 20:37
 */
@Data
@ToString
public class UserModel {
	private String name;
	private Long age;
	private List<String> hobbies;
	private Map<String, Long> clazz;
	private Dog dog;
}

@Data
@ToString
class Dog {
	private Long age;
	private String name;
}