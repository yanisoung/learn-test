package com.learn.test.demo.myThread.safe;

import com.learn.test.PrintUtils;
import com.learn.test.demo.myThread.executors.ThreadExecutors;
import com.learn.test.demo.model.Goods;
import com.learn.test.thead.SleepUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程不安全的生产者-消费者模式中的数据缓存区
 *
 * @author duomao
 */
public class SafeDataBuffer<T> {

    private static final Integer MAX_RUN_COUNT = 10;
    /**
     * 数据缓存区
     */
    private final List<T> dataList = new LinkedList<T>();

    private AtomicInteger runCnt = new AtomicInteger(0);


    /**
     * 生产者
     */
    public synchronized T add(T element) throws Exception {
        if (runCnt.get() >= MAX_RUN_COUNT) {
            PrintUtils.tco("缓存区已经满了");
            return null;
        }
        dataList.add(element);
        runCnt.incrementAndGet();
        int run = runCnt.get();
        int size = dataList.size();
        System.out.println(run + "  " + size);
        if (run != size) {
            String msg = "add" + runCnt + "!=" + dataList.size();
            throw new Exception(msg);
        }
        return element;
    }

    /**
     * 消费者
     */
    public synchronized T fetch() throws Exception {
        if (runCnt.get() <= 0) {
            PrintUtils.tco("缓存区已经无数据了");
            return null;
        }
        T element = dataList.remove(0);
        runCnt.decrementAndGet();
        if (runCnt.get() != dataList.size()) {
            String msg = "fetch" + runCnt + "!=" + dataList.size();
            throw new Exception(msg);
        }
        return element;
    }


    public static void main(String[] args) throws Exception {
        ExecutorService fixedThreadPool = ThreadExecutors.getFixedThreadPool(10);
        SafeDataBuffer<Goods> noBuffer = new SafeDataBuffer<>();
        for (int i = 0; i < MAX_RUN_COUNT; i++) {
            Long id = i + 1L;
            fixedThreadPool.submit(() -> {
                Goods goods = new Goods();
                goods.setId(id + "");
                goods.setPrice(10.0);
                goods.setName("商品-" + goods.getId());
                try {
                    noBuffer.add(goods);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            fixedThreadPool.submit(() -> {
                try {
                    noBuffer.fetch();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }
        fixedThreadPool.shutdown();
        SleepUtils.sleep(1000L);
    }

}
