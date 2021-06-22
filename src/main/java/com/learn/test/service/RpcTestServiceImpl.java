package com.learn.test.service;

import com.learn.test.rpc.RpcTestService;

/**
 * @author Bai
 * @date 2021/6/10 22:38
 */
public class RpcTestServiceImpl implements RpcTestService {

	@Override
	public String hello (String msg) {
		return "接收到了：" + msg;
	}
}
