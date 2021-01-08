package home.transaction.controller;

import com.alibaba.fastjson.JSON;
import home.transaction.dao.client.IAcountDaoManager;
import home.transaction.dto.UAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;
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


    @Autowired
    IAcountDaoManager iAcountDaoManager;

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


    @RequestMapping(value = "/yield")
    public void yieldThread() {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                logger.info(Thread.currentThread().getName() + "/" + i);
            }
        }, "AAA");
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                logger.info(Thread.currentThread().getName() + "/" + i);
            }
        }, "BBB");

        thread1.start();
        Thread.yield();
        thread2.start();

    }

    @RequestMapping(value = "/join")
    public void joinThread() throws InterruptedException {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    logger.info("runnable/" + i);
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        for (int i = 0; i < 100; i++) {
            if (i == 50) {
                thread.join();
            }
            logger.info("main/" + i);
        }

    }

    class ConditionResource {

        private int num = 1;
        private List<String> list = Collections.synchronizedList(new ArrayList<>());
        private Lock lock = new ReentrantLock();
        private Condition conditionA = lock.newCondition();
        private Condition conditionB = lock.newCondition();
        private Condition conditionC = lock.newCondition();

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

    @RequestMapping(value = "/callable")
    public String callAbleThread() {
        try {
            //callable可以异步来使用
            ShareResource resource = new ShareResource();
            FutureTask<String> task = new FutureTask<String>(resource);
            new Thread(task, "AAA").start();
            return task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.info(e.toString());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "什么也没有";
    }


    @RequestMapping(value = "/pool")
    public String threadPoolExecutor() {
        //查询所有的账户
        List<UAccount> accountList = iAcountDaoManager.getAccountList();
        //创建线程池处理数据
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                3,
                2,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        for (UAccount account : accountList) {
            executor.execute(() -> {
                logger.info(Thread.currentThread().getName() + " 线程池异步执行任务\t" + JSON.toJSONString(account));
            });
        }
        return JSON.toJSONString(accountList);
    }

    class ShareResource implements Callable<String> {

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

        @Override
        public String call() throws Exception {
            String result = UUID.randomUUID().toString().substring(0, 8);
            logger.info(Thread.currentThread().getName() + "\t callable异步执行成功 UUID:" + result);
            return result;
        }
    }

    @RequestMapping(value = "/semaphore")
    public void semaphoreMethod() {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                //占有资源
                try {
                    semaphore.acquire();
                    logger.info(finalI + "\t 抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //释放资源
                    semaphore.release();
                    logger.info(finalI + "\t 离开了车位");
                }
            }).start();
        }
    }
}
