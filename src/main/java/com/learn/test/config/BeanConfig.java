package com.learn.test.config;

import com.learn.test.demo.config.UserModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 *
 * @author Bai
 * @Configuration 标识当前类是个配置类
 * @date 2021/7/30 23:06
 */
@Configuration
public class BeanConfig {

	/**
	 * 将@Bean注解的方法返回值加载进spring容器
	 *
	 * @return
	 */
	@Bean
	public UserModel userModelConfigTest () {
		return new UserModel();
	}
}
