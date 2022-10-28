package com.learn.test.demo.myThread.safe;

import com.learn.test.demo.myThread.executors.ThreadExecutors;
import com.learn.test.demo.myThread.model.Goods;
import com.learn.test.thead.SleepUtils;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/**
 * 线程不安全的生产者-消费者模式 解耦V1版本
 * 消费者-生产者作为单独的类调用，无任何的耦合
 *
 * @author duomao
 */
public class NoSafeDemo {

    public static void main(String[] args) {
        ExecutorService fixedThreadPool = ThreadExecutors.getFixedThreadPool(20);
        NotSafeDataBuffer<Goods> notSafeDataBuffer = new NotSafeDataBuffer<>();

        //生产者
        Callable<Goods> producer = (() -> {
            Goods goods = new Goods();
            goods.setId(UUID.randomUUID().toString());
            goods.setName(goods.getId() + "商品名");
            notSafeDataBuffer.add(goods);
            return goods;
        });

        //消费者
        Callable<Goods> consumer = (notSafeDataBuffer::fetch);

        for (int i = 0; i < 10; i++) {
            fixedThreadPool.submit(new MyProducer(i + 1L, "生产者" + (i + 1) + "号", producer));
            fixedThreadPool.submit(new MyConsumer(i + 1L, "消费者" + (i + 1) + "号", consumer));
        }
        SleepUtils.sleep(9000L);
        fixedThreadPool.shutdown();
    }

}
