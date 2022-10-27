package com.learn.test.demo.myThread.safe.nosafe;

import com.alibaba.fastjson.JSONObject;
import com.learn.test.PrintUtils;
import com.learn.test.thead.SleepUtils;
import lombok.Data;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 消费者
 */
@Data
public class MyConsumer implements Runnable {

    /**
     * 默认间生产隔100毫秒
     */
    private final static Long CONSUMER_GEP = 150L;

    /**
     * 消费者id
     */
    private Long id;

    /**
     * 消费者姓名
     */
    private String name;

    /**
     * 消费者执行动作
     */
    private Callable action = null;

    /**
     * 预计执行次数
     */
    private Integer cunt = 10;

    /**
     * 消费次数
     */
    private AtomicInteger runCnt = new AtomicInteger(0);


    public MyConsumer(Long id, String name, Callable action) {
        this.id = id;
        this.name = name;
        this.action = action;
    }

    @Override
    public void run() {
//        while (true) {
        for (int i = 0; i < 100; i++) {
            try {
                runCnt.incrementAndGet();
                Object call = action.call();
                if (Objects.nonNull(call)) {
                    PrintUtils.oToStr("第" + runCnt.get() + "轮消费", JSONObject.toJSON(call));
                }
                SleepUtils.sleep(CONSUMER_GEP);
            } catch (Exception e) {
                PrintUtils.oToStr("MyConsumer", e);
            }
        }
    }
}
