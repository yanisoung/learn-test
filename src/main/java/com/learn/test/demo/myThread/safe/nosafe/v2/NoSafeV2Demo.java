package com.learn.test.demo.myThread.safe.nosafe.v2;

import com.learn.test.PrintUtils;
import com.learn.test.demo.myThread.executors.ThreadExecutors;
import com.learn.test.demo.myThread.model.Goods;
import com.learn.test.thead.SleepUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 线程不安全的生产者-消费者模式 解耦V1版本
 * 消费者-生产者作为单独的类调用，无任何的耦合
 *
 * @author duomao
 */
public class NoSafeV2Demo {


    private static final Integer MAX_COUNT = 5;
    /**
     * 数据缓存区
     */
    private static final List<Goods> GOODS_LIST = new ArrayList<>();

    private static AtomicLong GOODS_ID = new AtomicLong(1L);

    public static void main(String[] args) {
        ExecutorService fixedThreadPool = ThreadExecutors.getFixedThreadPool(10);
        //消费者
        Callable<Void> consumer = (() -> {
            SleepUtils.sleep(1000);
            if (GOODS_LIST.size() <= 0) {
                PrintUtils.tco("缓存区已经无数据了");
                return null;
            }
            Goods goods = GOODS_LIST.remove(0);
            PrintUtils.tco("取出来的商品：", goods);
            goods.setPrice(goods.getPrice() - 0.1);
            PrintUtils.tco("修改后的商品：", goods);
            PrintUtils.print(GOODS_LIST);
            return null;
        });

        //生产者
        Callable<Void> producer = (() -> {
            SleepUtils.sleep(500);
            if (GOODS_LIST.size() > MAX_COUNT) {
                PrintUtils.tco("缓存区已经满了");
                return null;
            }
            Goods goods = new Goods();
            goods.setId(GOODS_ID.getAndIncrement());
            goods.setPrice(10.0);
            goods.setName("商品" + goods.getId());
            GOODS_LIST.add(goods);
            return null;
        });

        for (int i = 0; i < 5; i++) {
            fixedThreadPool.submit(producer);
            fixedThreadPool.submit(consumer);
        }
        fixedThreadPool.shutdown();
        SleepUtils.sleep(90000L);
    }

}
