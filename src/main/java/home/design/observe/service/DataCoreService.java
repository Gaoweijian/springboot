/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package home.design.observe.service;

import com.alibaba.fastjson.JSON;
import home.design.observe.service.client.Observer;
import home.design.observe.service.client.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021/2/3, xxx Corporation Limited.
 * @Contact: xxx@xxx.com
 * @Date: 2021/2/3 下午 02:11
 * @Version: 1.0
 * @Description:
 */
@Service
public class DataCoreService implements Subject {

    private List<Observer> observers = new ArrayList();

    private final Logger logger = LoggerFactory.getLogger(DataCoreService.class);

    @Override
    public void registerObserver(Observer observer) {
        logger.info("观察者-订阅");
        if (observer != null) {
            if (observers.indexOf(observer) < 0) {
                observers.add(observer);
                logger.info("观察者-订阅成功");
            }
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        logger.info("观察者-取消订阅");
        if (observers.indexOf(observer) >= 0) {
            observers.remove(observer);
            logger.info("观察者-取消订阅成功");
        }
    }

    @Override
    public String notifyObserver(String msg) {
        logger.info("通知观察者" + msg);
        observers.forEach(
                (observer) -> {
                    observer.updateMsg(msg);
                    logger.info(observer.getName() + "/通知观察者成功");
                });
        return JSON.toJSONString(observers);
    }
}
