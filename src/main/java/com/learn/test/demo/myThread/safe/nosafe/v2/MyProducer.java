package com.learn.test.demo.myThread.safe.nosafe.v2;

import lombok.Data;

import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * 生产者
 */
@Data
public class MyProducer implements Runnable {

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

    public MyProducer(Long id, String name, Callable action) {
        this.id = id;
        this.name = name;
        this.action = action;
    }

    @Override
    public void run() {
        if (Objects.nonNull(action)) {
            try {
                action.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
