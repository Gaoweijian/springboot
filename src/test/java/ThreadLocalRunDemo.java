import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/3/8 下午 05:53
 * @Version: 1.0
 * @Description: 从这个类的名字就能大体了解到类的作用，ThreadLocal可以分解为Thread和Local，前者就不多说了，
 * 后者的意思是局部，本地的意思，整个类名可以理解为：线程局部对象或线程本地变量。程序是运行在线程中的，
 * 所以，在整个运行过程中，在任何地方都可以获得这个线程的局部对象，ThreadLocal类型的变量是和线程相绑定的。
 */
@Slf4j
public class ThreadLocalRunDemo {

    /**
     * 定义了一个ThreadLocal<Integer>对象，
     * 并重写它的initialValue方法，初始值是3
     * 这个对象会在三个线程间共享
     */
    private ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 3);
    /**
     * 设置一个信号量，许可数为1，让三个线程顺序执行
     */
    private Semaphore semaphore = new Semaphore(1);
    /**
     * 一个随机数
     */
    private Random random = new Random();

    /**
     * 每个线程中调用这个对象的get方法，再调用一个set方法设置一个随机值
     */
    public class Worker implements Runnable {
        @Override
        public void run() {

            try {
                // 随机延时1s以内的时间
                Thread.sleep(random.nextInt(1000));
                // 获取许可
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 从threadLocal中获取值
            int value = threadLocal.get();
            System.out.println(Thread.currentThread().getName() + " threadLocal old value : " + value);
            // 修改value值
            value = random.nextInt();
            // 新的value值放入threadLocal中
            threadLocal.set(value);
            System.out.println(Thread.currentThread().getName() + " threadLocal new value: " + value);
            System.out.println(Thread.currentThread().getName() + " threadLocal latest value : " + threadLocal.get());
            // 释放信号量
            semaphore.release();
            // 在线程池中,当线程退出之前一定要记得调用remove方法，因为在线程池中的线程对象是循环使用的
            threadLocal.remove();
        }
    }

    /**
     * 创建三个线程，每个线程都会对ThreadLocal对象进行操作
     */
    @Test
    public void runDemo() {
        ExecutorService es = Executors.newFixedThreadPool(3);
        ThreadLocalRunDemo threadLocalDemo = new ThreadLocalRunDemo();
        es.execute(threadLocalDemo.new Worker());
        es.execute(threadLocalDemo.new Worker());
        es.execute(threadLocalDemo.new Worker());
        es.shutdown();
    }

}
