package com.learn.test.demo.config;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.ToString;

/**
 * @author Bai
 * @date 2021/7/19 20:37
 */
@ToString
public class UserModel {
	private String name;
	private Long age;
	private List<String> hobbies;
	private Map<String, String> clazz;
	private Map<String, String> books;
	private Dog dog;

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public Long getAge () {
		return age;
	}

	public void setAge (Long age) {
		this.age = age;
	}

	public List<String> getHobbies () {
		return hobbies;
	}

	public void setHobbies (List<String> hobbies) {
		this.hobbies = hobbies;
	}

	public Map<String, String> getClazz () {
		return clazz;
	}

	public void setClazz (Map<String, String> clazz) {
		this.clazz = clazz;
	}

	public Map<String, String> getBooks () {
		return books;
	}

	public void setBooks (Map<String, String> books) {
		this.books = books;
	}

	public Dog getDog () {
		return dog;
	}

	public void setDog (Dog dog) {
		this.dog = dog;
	}
}

@Data
@ToString
class Dog {
	private Long age;
	private String name;
}