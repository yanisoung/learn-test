package com.learn.test.demo.myThread.threadlocal;

public class ThreadLocalHandler {

    /**
     * 使用static来修饰的好处：
     * 1.是为了让多个线程使用同一个ThreadLocal实例作为key，使用static来修饰可以保证所有对象共享同一个TreadLocal实例
     * 2.因为被static修饰的变量是绑定在类上的，当类被调用时静态变量也会被装载，静态变量只会分配一次内存空间，此类的所有实例都会共享这个内存空间，从而节约资源
     * 使用final来修饰的好处：
     * 是为了防止ThreadLocal在使用过程中发生动态变更 被意外更改，导致取出的值和放入的值不是同一个
     */
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

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
