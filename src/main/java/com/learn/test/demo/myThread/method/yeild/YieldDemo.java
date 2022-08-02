package com.learn.test.demo.myThread.method.yeild;

import com.learn.test.PrintUtils;
import com.learn.test.thead.SleepUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * yield方法demo
 * 1.线程调用yield方法后，释放占用CPU，处于就绪状态，并非阻塞状态
 * 2.就绪状态的线程被重新调度时也同样受优先级的影响，优先级高的线程在调用yield后 被重新调用的概率也更高
 * 3.yield调用后的线程也不能保证立马就变成就绪状态
 */
public class YieldDemo {

    private static Integer MAX = 1000;
    private static AtomicInteger INDEX = new AtomicInteger(0);
    private static Map<String, AtomicInteger> TOTAL = new HashMap<>();

    static class YieldThread extends Thread {
        static int threadNumber = 1;

        public YieldThread() {
            super("ThreadNumber-" + threadNumber);
            threadNumber++;
            TOTAL.put(this.getName(), new AtomicInteger(0));
        }


        @Override
        public void run() {
            for (int i = 0; i < MAX && INDEX.get() < MAX; i++) {
                PrintUtils.print(this.getName() + "，优先级：" + this.getPriority() + ",状态：" + this.getState());
                INDEX.incrementAndGet();
                TOTAL.get(this.getName()).incrementAndGet();
                if (i % 2 == 0) {
                    Thread.yield();
                    PrintUtils.print(this.getName() + "yield执行了！" + ",状态：" + this.getState());
                }
            }
            //输出所有线程的执行次数
            PrintUtils.toStr(TOTAL);
            PrintUtils.print(this.getName() + "运行结束了！");
        }
    }


    public static void main(String[] args) {
        //设置为最高的优先级
        Thread thread1 = new YieldThread();
        thread1.setPriority(Thread.MAX_PRIORITY);
        //设置为最低的优先级
        Thread thread2 = new YieldThread();
        thread2.setPriority(Thread.MIN_PRIORITY);
        PrintUtils.tco("启动线程.");
        thread1.start();
        thread2.start();
        SleepUtils.sleep(100);
    }

}
