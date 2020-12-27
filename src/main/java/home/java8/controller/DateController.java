package home.java8.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
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

        //Instant

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
    public String dateinstant(String key, String operate) throws InterruptedException {

        //默认获取UTC市区
        Instant instant = Instant.now();
        logger.info(instant.toString());


        //Duration 计算2个时间之间的间隔
        LocalTime time1 = LocalTime.now();
        TimeUnit.SECONDS.sleep(1);
        LocalTime time2 = LocalTime.now();
        Duration duration = Duration.between(time1, time2);
        logger.info("2个时间之间的间隔=" + duration.toMillis());


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        //Period 计算2个日期之间的间隔
        LocalDate localDate1 = LocalDate.parse("20201224", formatter);
        LocalDate localDate2 = LocalDate.parse("20201225", formatter);
        Period period = Period.between(localDate1, localDate2);
        logger.info("2个日期之间的间隔=" + period.getDays());


        //TemporalAdjuster:时间矫正器
        LocalDate localDate3 = LocalDate.now();
        LocalDate localDate4 = localDate3.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        logger.info("时间矫正器使用=" + localDate4.getDayOfWeek());


        return "";
    }

}
