package home.forkjoin.controller;

import home.forkjoin.service.ForkJoinExcutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2020, xxx Corporation Limited.
 * @Date: 2020/12/23 下午 07:19
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping(value = "/fork/join")
public class ForkJoinController {

    private final Logger logger = LoggerFactory.getLogger(ForkJoinController.class);

    /**
     * @描述
     * @参数 [startNum, endNum]
     * @返回值 java.lang.String
     * @创建人 gao侧耳倾听
     * @创建时间 2020/12/23
     * @修改人和其它信息
     */
    @RequestMapping(value = "/excutor")
    public String forkJoinExcutor(Long startNum, Long endNum) {
        Long startTime = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask forkJoinTask = new ForkJoinExcutor(startNum, endNum);
        pool.invoke(forkJoinTask);
        Long endTime = System.currentTimeMillis() - startTime;
        return String.valueOf("startNum=" + startNum + "  endNum=" + endNum + " sumNum=" + forkJoinTask.join() + "  times=" + endTime);

    }

    /**
     * @描述
     * @参数 [startNum, endNum]
     * @返回值 java.lang.String
     * @创建人 gao侧耳倾听
     * @创建时间 2020/12/23
     * @修改人和其它信息
     */
    @RequestMapping(value = "/recursion")
    public String recursionNum(Long startNum, Long endNum) {
        Long startTime = System.currentTimeMillis();
        Long sumNum = recursionAddNum(startNum, endNum);
        Long endTime = System.currentTimeMillis() - startTime;
        return String.valueOf("startNum=" + startNum + "  endNum=" + endNum + " sumNum=" + sumNum + "  times=" + endTime);
    }

    /**
     * @描述
     * @参数 [startNum, endNum]
     * @返回值 java.lang.Long
     * @创建人 gao侧耳倾听
     * @创建时间 2020/12/23
     * @修改人和其它信息
     */
    private Long recursionAddNum(Long startNum, Long endNum) {
        Long sumNum = 0L;
        for (long i = startNum; i <= endNum; i++) {
            sumNum += i;
        }
        return sumNum;
    }


    @RequestMapping(value = "/java8")
    public String java8Stream(Long startNum, Long endNum) {
        Long startTime = System.currentTimeMillis();
        Long sumNum = LongStream.range(startNum, endNum).parallel().reduce(startNum, Long::sum);
        Long endTime = System.currentTimeMillis() - startTime;
        return String.valueOf("startNum=" + startNum + "  endNum=" + endNum + " sumNum=" + sumNum + "  times=" + endTime);
    }

}
