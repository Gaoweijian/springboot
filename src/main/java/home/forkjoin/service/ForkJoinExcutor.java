package home.forkjoin.service;

import java.util.concurrent.RecursiveTask;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2020, xxx Corporation Limited.
 * @Date: 2020/12/23 下午 07:36
 * @Version: 1.0
 * @Description:
 */
public class ForkJoinExcutor extends RecursiveTask<Long> {


    private final Long MAX_FORK = 10000L;

    private Long startNum;
    private Long endNum;

    public ForkJoinExcutor(Long startNum, Long endNum) {
        this.startNum = startNum;
        this.endNum = endNum;
    }

    @Override
    protected Long compute() {

        if (endNum - startNum <= MAX_FORK) {
            long sum = 0;
            for (long i = startNum; i <= endNum; i++) {
                sum = sum + i;
            }
            return sum;
        } else {

            long middle = (startNum + endNum) / 2;

            ForkJoinExcutor left = new ForkJoinExcutor(startNum, middle);
            left.fork();

            ForkJoinExcutor right = new ForkJoinExcutor(middle, endNum);
            right.fork();

            return left.join() + right.join();
        }
    }
}
