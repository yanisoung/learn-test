package com.learn.test.server;

/**
 * @author Bai
 * @date 2021/6/22 23:14
 */
public class RpcTest {

	public static void main (String[] args) {
		RpcProxyServer rpcProxyServer = new RpcProxyServer();
		rpcProxyServer.server();
	}
}
