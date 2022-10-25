package com.learn.test.demo.myThread.safe.nosafe.v2;

import lombok.Data;

import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * 消费者
 */
@Data
public class MyConsumer implements Runnable {

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

    public MyConsumer(Long id, String name, Callable action) {
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
