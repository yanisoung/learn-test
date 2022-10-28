package com.learn.test.demo.myThread.safe;

import com.alibaba.fastjson.JSONObject;
import com.learn.test.PrintUtils;
import com.learn.test.thead.SleepUtils;
import lombok.Data;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者
 */
@Data
public class MyProducer implements Runnable {

    /**
     * 默认间生产隔100毫秒
     */
    private final static Long PRODUCE_GEP = 50L;

    /**
     * 生产者id
     */
    private Long id;

    /**
     * 生产者姓名
     */
    private String name;

    /**
     * 生产者执行动作
     */
    private Callable action = null;

    /**
     * 预计执行次数
     */
    private Integer max = 10;

    /**
     * 生产次数
     */
    private AtomicInteger runCnt = new AtomicInteger(0);

    public MyProducer(Long id, String name, Callable action) {
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
                    PrintUtils.tcfo("第" + runCnt.get() + "轮生产", call);
                }
                SleepUtils.sleep(PRODUCE_GEP);
            } catch (Exception e) {
                PrintUtils.oToStr("MyProducer", e);
            }
        }
    }
}
