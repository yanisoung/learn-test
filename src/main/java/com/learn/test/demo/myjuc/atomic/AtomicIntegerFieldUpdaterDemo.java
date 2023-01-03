package com.learn.test.demo.myjuc.atomic;

import com.learn.test.PrintUtils;
import com.learn.test.demo.model.People;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 属性更新原子类：长整型
 */
public class AtomicIntegerFieldUpdaterDemo {

    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<People> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(People.class, "age");
        People people = new People();
        people.setName("1");
        people.setAge(0);
        PrintUtils.print(people);
        PrintUtils.printSplitLine();
        atomicIntegerFieldUpdater.set(people, 1);

        PrintUtils.print(people);
        PrintUtils.printSplitLine();

        People people2 = new People();
        people2.setName("2");
        people2.setAge(2);
        atomicIntegerFieldUpdater.compareAndSet(people, 2, 4);
        PrintUtils.print(people2);
        PrintUtils.printSplitLine();

    }
}
