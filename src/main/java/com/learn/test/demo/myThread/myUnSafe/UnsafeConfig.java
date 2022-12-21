package com.learn.test.demo.myThread.myUnSafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeConfig {

    public static Unsafe getUnsafe() {
        return Unsafe.getUnsafe();
    }

    public static Unsafe getUnsafeV2() {
        try {
            Field field = Unsafe.class.getField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
