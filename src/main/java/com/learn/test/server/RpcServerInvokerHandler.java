package com.learn.test.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

import com.learn.test.rpc.RpcRequest;

/**
 * @author Bai
 * @date 2021/6/9 22:11
 */
public class RpcServerInvokerHandler implements Runnable {

	private Socket socket;
	private Object service;

	public RpcServerInvokerHandler (Socket socket, Object service) {
		this.socket = socket;
		this.service = service;
	}

	@Override
	public void run () {
		System.out.println("开始执行了");
		ObjectInputStream objectInputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
			//输入流，获取我们跟client约定的对象
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			RpcRequest rpcRequest = (RpcRequest)objectInputStream.readObject();
			//反射执行 获取返回值
			Object result = doInvoke(rpcRequest);
			//输出流，输出返回结果
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			//序列化
			objectOutputStream.writeObject(result);
			objectOutputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != objectInputStream) {
				try {
					objectInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != objectOutputStream) {
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private Object doInvoke (RpcRequest rpcRequest) throws Exception {
		if (null == rpcRequest) {
			throw new RuntimeException("rpcRequest is null !");
		}
		//获取要请求的class类名
		String className = rpcRequest.getClassName();
		//获取要执行的方法名
		String methodName = rpcRequest.getMethod();
		//获取要执行的入参列表
		Object[] args = rpcRequest.getArgs();
		//获取参数的class类型，根据class类型来获取对应的方法
		Class<?>[] types = new Class[args.length];
		for (int i = 0; i < args.length; i++) {
			types[i] = args[i].getClass();
		}
		//反射序列化执行
		Class<?> aClass = Class.forName(className);
		//根据方法名和入参获取对应的方法对象
		Method method = aClass.getMethod(methodName, types);
		//执行
		return method.invoke(service, args);
	}
}
