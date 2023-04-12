package com.learn.test.demo.myjuc;

import java.util.concurrent.locks.Lock;

// 省略import
//封装锁的使用代码
public class IncrementData {
    public static int sum = 0;

    public static void lockAndFastIncrease(Lock lock) {

        lock.lock(); //step1：抢占锁
        try {
            //step2：执行临界区代码
            sum++;
        } finally {

            lock.unlock(); //step3：释放锁
        }
    }
    // 省略其他代码
}