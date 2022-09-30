package com.learn.test.demo.reference;

import com.learn.test.demo.list.MyArrayList;

import java.lang.ref.WeakReference;
import java.util.Objects;

public class WeakReferenceDemo {

    public static void main(String[] args) {
        //todo 为什么传入的实例不能被GC回收呢？
//        MyArrayList<String> myArrayList = new MyArrayList();
//        myArrayList.add("1");
//        myArrayList.add("3");
//        myArrayList.add("2");
//        WeakReference<MyArrayList> weakReference = new WeakReference<>(myArrayList);

        WeakReference<MyArrayList> weakReference = new WeakReference<>(new MyArrayList());
        weakReference.get().add("1");
        System.out.println(weakReference.get().get(0));
        if (Objects.nonNull(weakReference.get())) {
            System.out.println("GC前，对象：" + System.identityHashCode(weakReference));
        }
        //调用GC
        System.gc();

        if (Objects.nonNull(weakReference.get())) {
            System.out.println("GC后，对象：" + System.identityHashCode(weakReference.get()));
        } else {
            System.out.println("GC后，对象回收了：" + System.identityHashCode(weakReference.get()));
        }

    }
}
