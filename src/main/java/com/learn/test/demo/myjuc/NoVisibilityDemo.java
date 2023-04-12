package com.learn.test.demo.myjuc;

/**
 * 可见性问题
 */
public class NoVisibilityDemo {
    private static boolean isReady;

    private static int num;

    private static class NoVisibilityThread extends Thread {
        @Override
        public void run() {
            while (!isReady) {
                Thread.yield();
            }
            System.out.println(num);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NoVisibilityThread noVisibilityThread = new NoVisibilityThread();
        noVisibilityThread.start();

        num = 42;
        isReady = true;

        noVisibilityThread.join();

    }
}
