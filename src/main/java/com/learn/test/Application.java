package com.learn.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Bai
 * @date 2021/6/15 14:07
 */
//开启AspectJ的Proxy设置，使得SpringBoot容器可以解析aop配置
@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication(scanBasePackages = "com.learn.test")
@PropertySource(value = {"classpath:application.properties"})
public class Application {

	public static void main (String[] args) {
		//1.返回了IOC容器
		ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
		//2.查看spring boot 帮我们配置了哪些组件
		String[] beanDefinitionNames = run.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println(beanDefinitionName);
		}
		System.out.println("------------启动成功-----------");

	}
}
