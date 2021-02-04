package home.design.observe.controller;

import home.design.observe.service.client.Observer;
import home.design.observe.service.client.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021/2/3, xxx Corporation Limited.
 * @Contact: xxx@xxx.com
 * @Date: 2021/2/3 下午 02:45
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping(value = "/subject")
public class SubjectController {
    @Autowired
    private Subject subject;

    @RequestMapping(value = "/register/observer")
    public String registerObserver(@RequestBody Observer observer) {
        subject.registerObserver(observer);
        return observer.getName() + "/订阅者注册成功...";
    }

    @RequestMapping(value = "/remove/observer")
    public String removeObserver(@RequestBody Observer observer) {
        subject.removeObserver(observer);
        return observer.getName() + "/订阅者取消注册成功...";
    }

    @RequestMapping(value = "/observer/msg")
    public String sendMsgToObserver(String msg) {
        return subject.notifyObserver(msg);
    }
}
