package com.learn.test.demo.myThread.runnable;

import java.util.concurrent.atomic.AtomicInteger;

import com.learn.test.PrintUtils;

/**
 * 商品库存类
 *
 * @author Bai
 * @date 2022/4/27 22:19
 */
public class GoodsStockDemo implements Runnable {

	//库存
	//使用AtomicInteger保证线程安全
	private AtomicInteger goodsStock = new AtomicInteger(20);

	//每个员工都需要销售4件商品
	private Integer shellCnt = 4;

	@Override
	public void run () {
		for (int i = 0; i < shellCnt; i++) {
			PrintUtils.print(Thread.currentThread().getName() + "销售了1件，还剩余：" + goodsStock.decrementAndGet());
		}
	}
}
