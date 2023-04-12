package com.learn.test.demo.myjuc.myColletion;

import com.alibaba.fastjson.JSONObject;
import com.learn.test.thead.SleepUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * copyOnWriteArrayList demo
 */
public class CopyOnWiteArrayListDemo {

    public static List<String> DATA = null;

    public static Runnable getThread(CountDownLatch cnt) {
        return new Runnable() {
            @Override
            public void run() {
                if (null == DATA) {
                    DATA = new ArrayList<>();
                    DATA.add("开始");
                    DATA = Collections.synchronizedList(DATA);
                }
                SleepUtils.sleep(100);
                Iterator<String> iterator = DATA.iterator();
                if (iterator.hasNext()) {
                    DATA.add(Thread.currentThread().getName());
                }
                cnt.countDown();
            }
        };
    }


    public static void main(String[] args) {
        CountDownLatch cnt = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(getThread(cnt));
            thread.setName(i + "");
            thread.start();
        }
        try {
            cnt.await();
        } catch (Exception e) {
            System.out.printf(e.toString());
        }
        System.out.println(JSONObject.toJSONString(DATA));
    }


}
