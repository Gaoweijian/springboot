/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package home.design.observe.controller;

import home.design.observe.service.client.Observer;
import home.design.observe.service.client.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wb-gwj607956
 * @version $Id: SubjectController.java, v 0.1 2020年12月22日 14:08 wb-gwj607956 Exp $
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
