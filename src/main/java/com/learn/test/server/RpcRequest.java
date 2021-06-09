package com.learn.test.server;

import java.io.Serializable;

import lombok.Data;

/**
 * @author Bai
 * @date 2021/6/8 23:24
 */
@Data
public class RpcRequest implements Serializable {
	private String className;
	private String method;
	private Object[] args;
}
