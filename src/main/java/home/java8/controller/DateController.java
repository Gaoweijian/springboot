package home.java8.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2020, xxx Corporation Limited.
 * @Date: 2020/12/25 上午 10:46
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping(value = "/date")
public class DateController {
    private final Logger logger = LoggerFactory.getLogger(DateController.class);

    private ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    @RequestMapping(value = "/test")
    public String dateTest(String key, String operate) {
        //LocalDate LockDateTime LocalTime
        LocalDate localDate = LocalDate.now();
        logger.info(localDate.toString());

        LocalTime localTime = LocalTime.now();
        logger.info(localTime.toString());

        LocalDateTime localDateTime = LocalDateTime.now();
        logger.info(localDateTime.toString());

        logger.info(localDateTime.plusDays(1).toString());
        logger.info(localDateTime.minusDays(1).toString());

        return "";
    }

    @RequestMapping(value = "/test0")
    public String dateTest0(String key, String operate) {

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

        Callable<Date> callable = new Callable() {
            @Override
            public Date call() throws Exception {
                return format.parse("20201225");
            }
        };

        ExecutorService service = Executors.newFixedThreadPool(10);

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                3,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 100; i++) {
            try {
                logger.info(service.submit(callable).get().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        service.shutdown();
        return "";
    }

    @RequestMapping(value = "/test1")
    public String dateTest1(String key, String operate) {

//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Callable<Date> callable = new Callable() {
            @Override
            public Date call() throws Exception {
                return threadLocal.get().parse("2020-12-25");
            }
        };

        ExecutorService service = Executors.newFixedThreadPool(5);

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                3,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

//        List<Future<Date>> futureList = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < 10; i++) {
            try {
                logger.info(poolExecutor.submit(callable).get().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        poolExecutor.shutdown();
        return "";
    }

    @RequestMapping(value = "/test2")
    public String dateTest2(String key, String operate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyMMdd");

        Callable<LocalDate> callable = new Callable() {
            @Override
            public LocalDate call() throws Exception {
                return LocalDate.parse("20201225", formatter);
            }
        };

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                3,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 10; i++) {
            try {
                logger.info(poolExecutor.submit(callable).get().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        poolExecutor.shutdown();
        return "";
    }

    @RequestMapping(value = "/instant")
    public String dateinstant(String key, String operate) {

        //默认获取UTC市区
        Instant instant = Instant.now();
        logger.info(instant.toString());
        return "";
    }

}
