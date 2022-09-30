package com.learn.test.demo.myThread.threadlocal;

public class ThreadLocalHandler {

    /**
     * 使用static来修饰，是为了让多个线程的threadLocal做为key时都是同一个ThreadLocal的实例，节约资源
     */
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static ThreadLocal<String> getThreadLocal() {
        return threadLocal;
    }

    public static String get() {
        return threadLocal.get();
    }

    public static void set(String s) {
        threadLocal.set(s);
    }

    public static void remove() {
        threadLocal.remove();
    }
}
