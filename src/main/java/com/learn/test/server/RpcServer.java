package com.learn.test.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

import com.learn.test.thead.ThreadPoolExecutorUtil;
import org.springframework.stereotype.Component;

/**
 * @author Bai
 * @date 2021/6/8 23:22
 */
@Component
public class RpcServer {

	ThreadPoolExecutor threadPoolExecutor = ThreadPoolExecutorUtil.getThreadPoolExecutor();

	public void server () {
		//创建一个ServerSocket监听8080端口
		ServerSocket server = null;
		try {
			server = new ServerSocket(7001);
			//等待请求
			while (true) {
				Socket socket = server.accept();
				System.out.println("请求进来了");
				//线程级别的阻塞
				threadPoolExecutor.execute(new RpcServerInvokerHandler(socket));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
