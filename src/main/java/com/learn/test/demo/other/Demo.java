package com.learn.test.demo.other;

import com.learn.test.PrintUtils;
import com.learn.test.demo.config.UserModel;
import com.learn.test.thead.SleepUtils;
import org.openjdk.jol.vm.VM;

public class Demo {

    public static void main(String[] args) {
        SleepUtils.sleep(5000);

        UserModel userModel = new UserModel();
        PrintUtils.printClassLayout(userModel);
        int hasCode = userModel.hashCode();
        System.out.println(hasCode);
        System.out.println(VM.current().details());
        Thread thread = new Thread(() -> {
            synchronizedMethod(userModel);
        });
        thread.start();
//        SleepUtils.sleep(500);
        System.out.println("-------thread已经获得锁");
        PrintUtils.printClassLayout(userModel);

        synchronizedMethod(userModel);
        System.out.println("-------main抢占线程");
        PrintUtils.printClassLayout(userModel);

//        MyArrayList<UserModel> myArrayList = new MyArrayList<>();
//        PrintUtils.printClassLayout(myArrayList);
//        myArrayList.add(userModel);
//        PrintUtils.printClassLayout(myArrayList);
        SleepUtils.sleep(10000);
        PrintUtils.printClassLayout(userModel);
    }

    public static void synchronizedMethod(UserModel userModel) {
        synchronized (userModel) {
            for (int i = 0; i < 10000; i++) {
            }
            PrintUtils.printClassLayout(userModel);
        }
    }

}
