package home.forkjoin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.RecursiveTask;

import static java.util.stream.Collectors.toList;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2020, xxx Corporation Limited.
 * @Date: 2020/12/24 下午 08:47
 * @Version: 1.0
 * @Description:
 */
public class ForkJoinCreateString extends RecursiveTask<List<String>> {
    private final Long MAX_FORK = 1000L;

    private volatile Long startNum;
    private volatile Long endNum;

    public ForkJoinCreateString(Long startNum, Long endNum) {
        this.startNum = startNum;
        this.endNum = endNum;
    }

    @Override
    protected List<String> compute() {

        if ((endNum - startNum) <= MAX_FORK) {
            List<String> list = new ArrayList<String>((int) (endNum - startNum + 1));
            for (long i = startNum; i <= endNum; i++) {
                list.add(UUID.randomUUID().toString().substring(0, 4));
            }
            return list;
        } else {

            long middle = (startNum + endNum) / 2;

            ForkJoinCreateString left = new ForkJoinCreateString(startNum, middle);
            left.fork();

            ForkJoinCreateString right = new ForkJoinCreateString(middle + 1, endNum);
            right.fork();


            List<String> list = left.join().stream().collect(toList());
            list.addAll(right.join());
            return list;
        }
    }
}
