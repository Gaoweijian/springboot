package home.transaction.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wb-gwj607956
 * @version $Id: VolatileController.java, v 0.1 2020年12月18日 10:01 wb-gwj607956 Exp $
 * <p>
 * 验证volatile 1.可见性 2.不保证原子性 3.禁止指令重排
 */
@RestController
@RequestMapping(value = "/volatile")
//@Scope(value = "prototype")
public class VolatileController {

    //private CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
    //    System.out.println(Thread.currentThread().getName() + "\t " + num);
    //});

    private String VOLATILE_STATUS = "true";

    private volatile List<String> list;

    //验证可见性
    @RequestMapping(value = "/visible")
    public int vildateVisible(@RequestParam(value = "status") boolean status) {
        int countNum = 0;
        VolatileResource resource = new VolatileResource();
        new Thread(() -> {
            try {
                //暂停3秒
                TimeUnit.SECONDS.sleep(3);
                resource.setNum100();
                System.out.println(Thread.currentThread().getName() + " \t update" + (status ? resource.numVolatile : resource.numNoVolatile));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, status + "-volatile").start();
        while ((status ? resource.numVolatile : resource.numNoVolatile) == 0) {}
        System.out.println(Thread.currentThread().getName() + "\t last " + (status ? resource.numVolatile : resource.numNoVolatile));
        return (status ? resource.numVolatile : resource.numNoVolatile);
    }

    //验证原子性
    @RequestMapping(value = "/atomic")
    public int validateVolatile() {
        CountDownLatch downLatch = new CountDownLatch(5);
        AtomicInteger num = new AtomicInteger();
        Thread thread = Thread.currentThread();
        System.out.println("当前主线程 \t" + thread.getName() + "\t" + this.toString());
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t validateVolatile");
                for (int j = 0; j < 2000; j++) {
                    num.getAndIncrement();
                }
                downLatch.countDown();
            }, "" + i).start();
        }
        try {
            downLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return num.get();
    }

    //验证原子性
    @RequestMapping(value = "/atomic/list")
    public String validateVolatileList() {
        list = new ArrayList<>();
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t 验证原子性");
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                list.add(finalI + "");
            }, "" + i).start();
        }
        System.out.println(thread.getName() + "\t list：" + JSON.toJSONString(list));
        return JSON.toJSONString(list);
    }

    class VolatileResource {

        public volatile int numVolatile = 0;

        public int numNoVolatile = 0;

        public void setNum100() {
            numVolatile = 100;
            numNoVolatile = 100;
        }
    }

}
