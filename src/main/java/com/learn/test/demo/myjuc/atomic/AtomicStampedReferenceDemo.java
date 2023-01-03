package com.learn.test.demo.myjuc.atomic;

import com.learn.test.PrintUtils;
import com.learn.test.demo.list.MyArrayList;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 原子操作类：带时间戳
 */
public class AtomicStampedReferenceDemo {

    public static void main(String[] args) {
        MyArrayList<String> myArrayList = new MyArrayList();
        myArrayList.add("1");

        AtomicStampedReference<MyArrayList> atomicReference = new AtomicStampedReference<>(myArrayList, 1000);

        int[] ints = new int[1];
        PrintUtils.print(atomicReference.get(ints));
        PrintUtils.printSplitLine();

        MyArrayList<Integer> update = new MyArrayList();
        update.add(2);

        //更新失败
        atomicReference.compareAndSet(null, update, 1100, 20000);
        //["1"]
        PrintUtils.print(atomicReference.get(ints));
        PrintUtils.printSplitLine();

        atomicReference.compareAndSet(myArrayList, update, 1300, 20000);
        //[1]
        PrintUtils.print(atomicReference.get(ints));


        atomicReference.compareAndSet(myArrayList, update, 1000, 20000);
        //[2]
        PrintUtils.print(atomicReference.get(ints));

    }
}
