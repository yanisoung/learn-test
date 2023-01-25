package com.learn.test.demo.myjuc.atomic;

import com.learn.test.PrintUtils;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 原子操作类型：boolean类型
 */
public class AtomicBooleanDemo {
    public static void main(String[] args) {
        AtomicBoolean atomicReference = new AtomicBoolean(true);

        atomicReference.getAndSet(false);
        PrintUtils.print(atomicReference.get());
        PrintUtils.printSplitLine();

        atomicReference.getAndSet(true);
        PrintUtils.print(atomicReference.get());
        PrintUtils.printSplitLine();

    }
}
