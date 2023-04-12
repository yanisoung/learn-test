package com.learn.test.demo.myjuc.myAQS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

// 省略import
public class SimpleMockLock implements Lock {
    //同步器实例
    private final Sync sync = new Sync();

    // 自定义的内部类：同步器
    // 直接使用 AbstractQueuedSynchronizer.state 值表示锁的状态
    // AbstractQueuedSynchronizer.state=1 表示锁没有被占用
    // AbstractQueuedSynchronizer.state=0 表示锁没已经被占用
    private static class Sync extends AbstractQueuedSynchronizer {
        //钩子方法
        protected boolean tryAcquire(int arg) {
            //CAS更新状态值为1
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        //钩子方法
        protected boolean tryRelease(int arg) {
            //如果当前线程不是占用锁的线程
            if (Thread.currentThread() != getExclusiveOwnerThread()) {
                //抛出非法状态的异常
                throw new IllegalMonitorStateException();
            }
            //如果锁的状态为没有占用
            if (getState() == 0) {
                //抛出非法状态的异常
                throw new IllegalMonitorStateException();
            }
            //接下来不需要使用CAS操作，因为下面的操作不存在并发场景
            setExclusiveOwnerThread(null);
            //设置状态
            setState(0);
            return true;
        }
    }

    //显式锁的抢占方法
    @Override
    public void lock() {
        //委托给同步器的acquire()抢占方法
        sync.acquire(1);
    }

    //显式锁的释放方法
    @Override
    public void unlock() {
        //委托给同步器的release()释放方法
        sync.release(1);
    }


    // 省略其他未实现的方法

    @Override
    public Condition newCondition() {
        return null;
    }


    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }


}
