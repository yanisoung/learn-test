package com.learn.test.demo.myjuc.myAQS;

public class OrderingExample {
    private static int x = 0;
    private static boolean ready = false;

    public void startThread() {
        new Thread(new Runnable() {
            public void run() {
                x = 1;
                ready = true;
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                while (!ready) {
                    Thread.yield();
                }
                System.out.println("x = " + x);
            }
        }).start();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            OrderingExample orderingExample = new OrderingExample();
            orderingExample.startThread();
        }
    }

}
