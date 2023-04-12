package com.learn.test.demo.myThread.volatitle;

public class VolatileVar {

    volatile int var = 0;

    public void setVar(int var) {
        System.out.println("var:" + var);
        this.var = var;
    }

    public static void main(String[] args) {
        VolatileVar volatileVar = new VolatileVar();
        volatileVar.setVar(100);
    }
}
