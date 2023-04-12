package com.learn.test.demo.myjuc.myColletion;

import com.learn.test.PrintUtils;
import com.learn.test.thead.SleepUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// 省略import
public class CopyOnWriteArrayListTest {
    //并发操作的执行目标
    public static class CocurrentTarget implements Runnable {
        //并发操作的目标队列
        List<String> targetList = null;

        public CocurrentTarget(List<String> targetList) {
            this.targetList = targetList;
        }

        @Override
        public void run() {
            Iterator<String> iterator = targetList.iterator();
            //迭代操作
            while (iterator.hasNext()) {
                // 在迭代操作时，进行列表的修改
                String threadName = Thread.currentThread().getName();
                PrintUtils.tco("开始往同步队列加入线程名称：" + threadName);
                targetList.add(threadName);
            }
        }
    }

    //测试同步队列：在迭代操作时，进行列表的修改
    @Test
    public void testSynchronizedList() {
        List<String> notSafeList = Arrays.asList("a", "b", "c");
        List<String> synList = Collections.synchronizedList(notSafeList);
        //创建一个执行目标
        CocurrentTarget synchronizedListListDemo = new CocurrentTarget(synList);
        //10个线程并发
        for (int i = 0; i < 10; i++) {
            new Thread(synchronizedListListDemo, "线程" + i).start();
        }
        //主线程等待
        SleepUtils.sleep(1000);
    }
}
