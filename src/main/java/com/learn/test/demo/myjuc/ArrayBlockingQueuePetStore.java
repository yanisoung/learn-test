package com.learn.test.demo.myjuc;


import com.learn.test.demo.model.Goods;
import com.learn.test.demo.myThread.safe.MyConsumer;
import com.learn.test.demo.myThread.safe.MyProducer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArrayBlockingQueuePetStore {

    public static final int MAX_AMOUNT = 10; //数据区长度

    //共享数据区，类定义
    static class DataBuffer<T> {
        //使用阻塞队列保存数据
        private ArrayBlockingQueue<T> dataList = new ArrayBlockingQueue<>(MAX_AMOUNT);

        // 向数据区增加一个元素，委托给阻塞队列
        public void add(T element) throws Exception {
            dataList.add(element); //直接委托
        }

        /**
         * 从数据区取出一个商品，委托给阻塞队列
         */
        public T fetch() throws Exception {
            return dataList.take();  //直接委托
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.setErr(System.out);
        //共享数据区，实例对象
        DataBuffer<Goods> dataBuffer = new DataBuffer<>();

        //生产者执行的操作
        Callable<Goods> produceAction = () ->
        {
            //首先生成一个随机的商品
            Goods goods = new Goods();
            //将商品加上共享数据区
            dataBuffer.add(goods);
            return goods;
        };
        //消费者执行的操作
        Callable<Goods> consumerAction = () -> {
            // 从PetStore获取商品
            Goods goods = null;
            goods = dataBuffer.fetch();
            return goods;
        };
        // 同时并发执行的线程数
        final int THREAD_TOTAL = 20;
        // 线程池，用于多线程模拟测试
        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_TOTAL);

        // 假定共11个线程，其中有10个消费者，但是只有1个生产者
        final int CONSUMER_TOTAL = 11;
        final int PRODUCE_TOTAL = 1;

        for (int i = 0; i < PRODUCE_TOTAL; i++) {
            //生产者线程每生产一个商品，间隔50毫秒
            threadPool.submit(new MyProducer(i + 1L, "" + i + 1, produceAction));
        }
        for (int i = 0; i < CONSUMER_TOTAL; i++) {
            //消费者线程每消费一个商品，间隔100毫秒
            threadPool.submit(new MyConsumer(i + 1L, "" + i + 1, consumerAction));
        }
        threadPool.shutdown();
    }
}
