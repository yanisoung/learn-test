实现线程的4种方式
1.继承Thread类 
2.实现Runnable接口
3.使用线程池
4.使用工具类

优雅实现Runnable类的两种方式：
1)使用内部匿名类实现，demo:com.learn.test.demo.myThread.runnable.AnonymousCreateDemo
2)使用Lambda表达式实现，demo:com.learn.test.demo.myThread.runnable.LambdaCreateThreadDemo
注：使用内部匿名类和使用lambda表达式实现的区别并不大

实现Runnable类创建线程的优缺点
缺点：
1.无法直接访问Thread类相关属性和方法，也无法直接控制线程，需要借助Thread类的静态方法currentThread获取当前线程来实现相关操作
2.实现Runnable的类并不是线程类，而是线程类的target执行目标类，使用时需要将实现类的实例作为入参传入Thread类的构造，实现类的run方法才可以被调用
优点：
1.解耦，可扩展性好
2.适用于多个线程需要共享资源的场景，例如库存的增减 demo:com.learn.test.demo.myThread.ThreadDemo.goodsStockTest


继承Thread类和实现Runnable类的区别：
1.继承Thread类可以更好的实现多线程的并发，使每个线程更专注的完成各自的任务
2.实现Runnable类可以更好的实现多个线程并发的完成同一个任务，访问共享资源
3.当实现Runnable类并且多个线程共享资源时，需要使用原子类数据类型或是限制同步操作
