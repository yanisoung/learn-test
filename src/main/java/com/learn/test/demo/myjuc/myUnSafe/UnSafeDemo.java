package com.learn.test.demo.myjuc.myUnSafe;

import com.learn.test.PrintUtils;
import com.learn.test.demo.myThread.myUnSafe.UnsafeConfig;
import com.learn.test.thead.ThreadPoolExecutorUtil;
//import sun.misc.Unsafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;

public class UnSafeDemo {

    //并发数量
    private static final int THREAD_COUNT = 10;

    // 基于CAS无锁实现的安全自增
    static class OptimisticLockingPlus {

        //内部值，使用volatile保证线程可见性
        private volatile int value;//值

        //不安全类
//        private static final Unsafe unsafe = UnsafeConfig.getUnsafeV2();

        //value 的内存偏移（相对于对象头部的偏移，不是绝对偏移）
        private static final long valueOffset;

        //统计失败的次数
        private final AtomicLong failure = new AtomicLong(0);

        static {
            try {
                //取得value属性的内存偏移
                valueOffset =0;
//                valueOffset = unsafe.objectFieldOffset(OptimisticLockingPlus.class.getDeclaredField("value"));

//                PrintUtils.tco("valueOffset:=" + valueOffset);
            } catch (Exception ex) {
                throw new Error(ex);
            }
        }

        //通过CAS原子操作，进行“比较并交换”
        public final boolean unSafeCompareAndSet(int oldValue, int newValue) {
            //原子操作：使用unsafe的“比较并交换”方法进行value属性的交换
//            return unsafe.compareAndSwapInt(this, valueOffset, oldValue, newValue);
            return false;
        }

        //使用无锁编程实现安全的自增方法
        public void selfPlus() {
            int oldValue = value;
            //通过CAS原子操作，如果操作失败就自旋，一直到操作成功
            do {
                // 获取旧值
                oldValue = value;
                //记录失败的次数
                failure.incrementAndGet();

            } while (!unSafeCompareAndSet(oldValue, oldValue + 1));
        }
    }

    //测试用例入口方法
    public static void main(String[] args) throws Exception {
        final OptimisticLockingPlus cas = new OptimisticLockingPlus();
        //倒数闩，需要倒数THREAD_COUNT次
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            // 提交10个任务
            ThreadPoolExecutor threadPoolExecutor = ThreadPoolExecutorUtil.getThreadPoolExecutor();
            threadPoolExecutor.submit(() -> {
                //每个任务累加1000次
                for (int j = 0; j < 1000; j++) {
                    cas.selfPlus();
                }
                latch.countDown();  // 执行完一个任务，倒数闩减少一次
            });
        }
        latch.await(); //主线程等待倒数闩倒数完毕
        PrintUtils.tco("累加之和：" + cas.value);
        PrintUtils.tco("失败次数：" + cas.failure.get());
    }
}
