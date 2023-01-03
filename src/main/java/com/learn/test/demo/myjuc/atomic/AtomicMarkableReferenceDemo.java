package com.learn.test.demo.myjuc.atomic;

import com.learn.test.PrintUtils;
import com.learn.test.demo.list.MyArrayList;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * 带修改标识操作符的原子类
 */
public class AtomicMarkableReferenceDemo {

    public static void main(String[] args) {
        MyArrayList<String> myArrayList = new MyArrayList();
        myArrayList.add("1");

        AtomicMarkableReference<MyArrayList> atomicReference = new AtomicMarkableReference<>(myArrayList, false);
        boolean[] result = new boolean[1];
        PrintUtils.print(atomicReference.get(result));
        PrintUtils.printSplitLine();

        MyArrayList<Integer> update = new MyArrayList();
        update.add(2);

        //更新失败
        atomicReference.compareAndSet(null, update, false, true);
        //["1"]
        PrintUtils.print(atomicReference.get(result));
        PrintUtils.printSplitLine();

        atomicReference.compareAndSet(myArrayList, update, false, false);
        //[2]
        PrintUtils.print(atomicReference.get(result));

        update.set(0, 3);

        atomicReference.compareAndSet(myArrayList, update, false, true);
        //[3]
        PrintUtils.print(atomicReference.get(result));

    }
}
