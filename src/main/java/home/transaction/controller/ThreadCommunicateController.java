package home.transaction.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2020, xxx Corporation Limited.
 * @Date: 2020/12/20 上午 10:34
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("/thread/communicat")
public class ThreadCommunicateController {

    private final Logger logger = LoggerFactory.getLogger(ThreadCommunicateController.class);

    @RequestMapping(value = "/concurrency")
    public String concurrencyThread() {
        ShareResource resource = new ShareResource();
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                resource.increment();
                countDownLatch.countDown();
            }, "increment-" + i).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                resource.decrement();
                countDownLatch.countDown();
            }, "decrement-" + i).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return JSON.toJSONString(resource.list);
    }
    
    
    @RequestMapping(value = "/condition")
    public String threeConditionTry(int round) {

        CountDownLatch downLatch = new CountDownLatch(3);
        ConditionResource resource = new ConditionResource();

        new Thread(() -> {
            for (int i = 0; i < round; i++) {
                resource.accountA();
            }
            downLatch.countDown();
        }, "accountA").start();

        new Thread(() -> {
            for (int i = 0; i < round; i++) {
                resource.accountB();
            }
            downLatch.countDown();

        }, "accountB").start();

        new Thread(() -> {
            for (int i = 0; i < round; i++) {
                resource.accountC();
            }
            downLatch.countDown();
        }, "accountC").start();
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(resource.list);
    }

    @RequestMapping(value = "/dead/lock")
    public String deadLock() {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new DeadLockResource(lockA, lockB), "AAA").start();
        new Thread(new DeadLockResource(lockB, lockA), "BBB").start();
        return "deadLock模拟";
    }

    class DeadLockResource implements Runnable {

        private String lockA;
        private String lockB;

        public DeadLockResource(String lockA, String lockB) {
            this.lockA = lockA;
            this.lockB = lockB;
        }

        @Override
        public void run() {
            synchronized (lockA) {
                logger.info(Thread.currentThread().getName() + "\t 当前持有锁" + lockA + "\t 尝试持有锁：" + lockB);
                synchronized (lockB) {
                    logger.info(Thread.currentThread().getName() + "\t 当前持有锁" + lockB + "\t 尝试持有锁：" + lockA);
                }
            }
        }
    }

    class ConditionResource {

        private int          num        = 1;
        private List<String> list       = Collections.synchronizedList(new ArrayList<>());
        private Lock         lock       = new ReentrantLock();
        private Condition    conditionA = lock.newCondition();
        private Condition    conditionB = lock.newCondition();
        private Condition    conditionC = lock.newCondition();

        public void accountA() {
            try {
                lock.lock();
                while (num != 1) {
                    conditionA.await();
                }
                num = 2;
                list.add(Thread.currentThread().getName());
                conditionB.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

        public void accountB() {
            try {
                lock.lock();
                while (num != 2) {
                    conditionB.await();
                }
                num = 3;
                list.add(Thread.currentThread().getName());
                conditionC.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

        public void accountC() {
            try {
                lock.lock();
                while (num != 3) {
                    conditionC.await();
                }
                num = 1;
                list.add(Thread.currentThread().getName());
                conditionA.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

    }

    class ShareResource {

        private List<String> list = Collections.synchronizedList(new ArrayList<>());
        private int num = 0;
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        /**
         * @描述
         * @参数 []
         * @返回值 void
         * @创建人 gao侧耳倾听
         * @创建时间 2020/12/20
         * @修改人和其它信息
         */
        public void increment() {
            try {
                lock.lock();
                while (num != 0) {
                    condition.await();
                }
                num++;
                list.add(Thread.currentThread().getName() + "===" + num);
                logger.info(Thread.currentThread().getName() + "===" + num);
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        /**
         * @描述
         * @参数 []
         * @返回值 void
         * @创建人 gao侧耳倾听
         * @创建时间 2020/12/20
         * @修改人和其它信息
         */
        public void decrement() {

            try {
                lock.lock();
                while (num != 1) {
                    condition.await();
                }
                num--;
                list.add(Thread.currentThread().getName() + "===" + num);
                logger.info(Thread.currentThread().getName() + "===" + num);
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }
}
