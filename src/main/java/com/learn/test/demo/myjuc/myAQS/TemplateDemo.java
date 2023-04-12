package com.learn.test.demo.myjuc.myAQS;

import com.learn.test.PrintUtils;

public class TemplateDemo {

    static abstract class AbstractAction {
        /**
         * 模板方法：算法骨架
         */
        public void tempMethod() {
            PrintUtils.toStr("模板方法的算法骨架被执行");
            beforeAction();    // 执行前的公共操作
            action();                          // 调用钩子方法
            afterAction();             // 执行后的公共操作
        }

        /**
         * 执行前
         */
        protected void beforeAction() {
            PrintUtils.toStr("准备执行钩子方法");
        }

        /**
         * 钩子方法：这里定义为一个抽象方法
         */
        public abstract void action();

        /**
         * 执行后
         */
        private void afterAction() {
            PrintUtils.toStr("钩子方法执行完成");
        }
    }

    //子类A：提供了钩子方法实现
    static class ActionA extends AbstractAction {
        /**
         * 钩子方法的实现
         */
        @Override
        public void action() {
            PrintUtils.toStr("钩子方法的实现 ActionA.action() 被执行");
        }
    }

    //子类B：提供了钩子方法实现
    static class ActionB extends AbstractAction {
        /**
         * 钩子方法的实现
         */
        @Override
        public void action() {
            PrintUtils.toStr("钩子方法的实现 ActionB.action() 被执行");
        }
    }

    public static void main(String[] args) {
        AbstractAction action = null;

        //创建一个 ActionA 实例
        action = new ActionA();
        //执行基类的模板方法
        action.tempMethod();

        //创建一个 ActionB 实例
        action = new ActionB();
        //执行基类的模板方法
        action.tempMethod();
    }
}