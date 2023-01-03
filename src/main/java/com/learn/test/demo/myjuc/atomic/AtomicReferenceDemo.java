package com.learn.test.demo.myjuc.atomic;

import com.learn.test.PrintUtils;
import com.learn.test.demo.list.MyArrayList;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子操作类型：引用类型
 * 保证的是内存地址值修改，不保证对象的属性
 */
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        AtomicReference<MyArrayList> atomicReference = new AtomicReference<>();
        MyArrayList<String> myArrayList = new MyArrayList();
        myArrayList.add("1");
        atomicReference.set(myArrayList);

        //["1"]
        PrintUtils.print(atomicReference.get());
        PrintUtils.printSplitLine();

        MyArrayList<Integer> update = new MyArrayList();
        update.add(2);

        //更新失败
        atomicReference.compareAndSet(null, update);
        //["1"]
        PrintUtils.print(atomicReference.get());
        PrintUtils.printSplitLine();

        atomicReference.compareAndSet(myArrayList, update);
        //[2]
        PrintUtils.print(atomicReference.get());

    }
}
