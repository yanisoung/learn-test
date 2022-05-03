实现线程的4种方式 1.继承Thread类 2.实现Runnable接口 3.使用Callable和FutureTask类创建线程池 4.使用线程池

优雅实现Runnable类的两种方式： 1)使用内部匿名类实现，demo:com.learn.test.demo.myThread.runnable.AnonymousCreateDemo 2)使用Lambda表达式实现，demo:
com.learn.test.demo.myThread.runnable.LambdaCreateThreadDemo 注：使用内部匿名类和使用lambda表达式实现的区别并不大

实现Runnable类创建线程的优缺点 缺点： 1.无法直接访问Thread类相关属性和方法，也无法直接控制线程，需要借助Thread类的静态方法currentThread获取当前线程来实现相关操作
2.实现Runnable的类并不是线程类，而是线程类的target执行目标类，使用时需要将实现类的实例作为入参传入Thread类的构造，实现类的run方法才可以被调用 优点： 1.解耦，可扩展性好
2.适用于多个线程需要共享资源的场景，例如库存的增减 demo:com.learn.test.demo.myThread.ThreadDemo.goodsStockTest

继承Thread类和实现Runnable类的区别： 1.继承Thread类可以更好的实现多线程的并发，使每个线程更专注的完成各自的任务 2.实现Runnable类可以更好的实现多个线程并发的完成同一个任务，访问共享资源
3.当实现Runnable类并且多个线程共享资源时，需要使用原子类数据类型或是限制同步操作

继承Thread类和实现Runnable类的缺点：两者都没有返回异步执行结果，在某些需要关心返回值的场景下，这两种创建方式就不适用了

使用Callable和FutureTask创建线程： 1.实现Callable接口 2.将Callable接口的实现类作为FutureTask构造的入参传入 3.将FutureTask实例作为target属性传入Thread类的构造
4.调用Thread类的start方法，启动线程 demo:com.learn.test.demo.myThread.ThreadDemo.callable 调用链路： Thread.start()->Thread.run()->
Callable实现类.call()->将call接口执行结果赋值给FutureTask的属性outcome FutureTask.get()->outcome属性 实现逻辑（详情可参见 尼恩 《Java并发核心编程（卷2）》多线程篇）：
FutureTask是RunnableFuture的默认实现类，RunnableFuture是继承了Runnable和Future，所以FutureTask同时具有Runnable的run方法和Future的get方法，
具有异步执行和获取异步返回值的能力。在调用FutureTask的run方法时，run方法内部会回调Future的call方法。

使用线程池创建线程（实际生产中禁止使用Executors创建线程） 
1.execute和submit方法的区别：
（入参）execute的入参是Runnable的实例，submit的入参可以是带FutureTask的实例也可以是Runnable的实例还可以是Thread的实例。 
（返回值）execute无返回值，submit有返回值。
execute是Executors的方法，submit是子类ExecutorService的方法。
