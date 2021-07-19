package com.learn.test.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 尝试将yml文件中的属性封装进这个类中
 *
 * @author Bai
 * @date 2021/7/19 20:36
 */
//将这个类注入spring容器
@Component
//将属性注入这个类
@ConfigurationProperties(prefix = "configdemo")
public class ConfigModelDemo {
	private UserModel user;
}
