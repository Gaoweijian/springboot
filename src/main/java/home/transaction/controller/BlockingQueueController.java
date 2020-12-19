package home.transaction.controller;

import com.alibaba.fastjson.JSON;
import home.transaction.enums.QueueTypeEnum;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;

import static home.transaction.enums.QueueTypeEnum.getIdentificationCode;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2020, xxx Corporation Limited.
 * @Date: 2020/12/19 上午 10:42
 * @Version: 1.0
 * @Description: 阻塞队列验证
 */
@RestController
@RequestMapping(value = "/block/queue")
public class BlockingQueueController {

    private BlockingQueue<String> arrayQueue = new ArrayBlockingQueue<String>(1);
    private BlockingQueue<String> linkedDeque = new LinkedBlockingDeque<>(1);
    private BlockingQueue<String> synchronousQueue = new SynchronousQueue<String>();

    /**
     * @描述
     * @参数 [queueType, operate, value]
     * @返回值 java.lang.String
     * @创建人 gao侧耳倾听
     * @创建时间 2020/12/19
     * @修改人和其它信息
     */
    @RequestMapping(value = "/push")
    public String validatePushQueue(String queueType, String operate, String value) {
        QueueTypeEnum typeEnum = getIdentificationCode(queueType);
        try {
            switch (typeEnum) {
                case ARRAY_BLOCKING_QUEUE:
                    operateQueue(operate, value);
                    return JSON.toJSONString(arrayQueue);
                case LINKED_BLOCKING_QUEUE:
                    operateQueue(operate, value);
                    return JSON.toJSONString(linkedDeque);
                case SYNCHRONOUS_QUEUE:
                    operateQueue(operate, value);
                    return JSON.toJSONString(synchronousQueue);
                default:
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
        return "什么也没有";
    }

    /**
     * @描述
     * @参数 [queueType, operate, value]
     * @返回值 java.lang.String
     * @创建人 gao侧耳倾听
     * @创建时间 2020/12/19
     * @修改人和其它信息
     */
    @RequestMapping(value = "/delete")
    public String validateDeleteQueue(String queueType, String operate) {
        try {
            deleteQueue(operate);
        } catch (Exception e) {
            return e.getMessage();
        }
        return queueType;
    }

    /**
     * @描述
     * @参数 [operate, value]
     * @返回值 void
     * @创建人 gao侧耳倾听
     * @创建时间 2020/12/19
     * @修改人和其它信息
     */
    private String operateQueue(String operate, String value) throws InterruptedException {
        try {
            switch (operate) {
                case "add":
                    arrayQueue.add(value);
                    return JSON.toJSONString(arrayQueue);
                case "offer":
                    linkedDeque.offer(value);
                    return JSON.toJSONString(linkedDeque);
                case "put":
                    synchronousQueue.put(value);
                    return JSON.toJSONString(synchronousQueue);
                default:
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw e;
        }
        return "什么也没有";
    }

    /**
     * @描述
     * @参数 [operate]
     * @返回值 void
     * @创建人 gao侧耳倾听
     * @创建时间 2020/12/19
     * @修改人和其它信息
     */
    private void deleteQueue(String operate) throws InterruptedException {
        try {
            switch (operate) {
                case "remove":
                    arrayQueue.remove();
                    break;
                case "poll":
                    linkedDeque.poll();
                    break;
                case "take":
                    synchronousQueue.take();
                    break;
                default:
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
