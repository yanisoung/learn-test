package com.learn.test.demo.myThread.threadlocal;

public class ThreadLocalHandler {
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
