package com.learn.test.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

import com.learn.test.rpc.RpcTestService;
import com.learn.test.service.RpcTestServiceImpl;
import com.learn.test.thead.ThreadPoolExecutorUtil;

/**
 * @author Bai
 * @date 2021/6/8 23:22
 */
public class RpcProxyServer {

	ThreadPoolExecutor threadPoolExecutor = ThreadPoolExecutorUtil.getThreadPoolExecutor();

	public void server () {
		//创建一个ServerSocket监听7003端口
		ServerSocket server = null;
		RpcTestService rpcTestService = new RpcTestServiceImpl();
		try {
			server = new ServerSocket(7003);
			//等待请求
			while (true) {
				Socket socket = server.accept();
				System.out.println("请求进来了");
				//线程级别的阻塞
				threadPoolExecutor.execute(new RpcServerInvokerHandler(socket, rpcTestService));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
