/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package home.design.observe.service.client;


/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021/2/3, xxx Corporation Limited.
 * @Contact: xxx@xxx.com
 * @Date: 2021/2/3 下午 01:49
 * @Version: 1.0
 * @Description: 观察者模式
 * 定义了对象之间的一对多的依赖，这样一来，当一个对象改变时，它的所有的依赖者都会收到通知并自动更新。
 * 对于JDK或者Andorid中都有很多地方实现了观察者模式，比如XXXView.addXXXListenter ，
 * 当然了 XXXView.setOnXXXListener不一定是观察者模式，因为观察者模式是一种一对多的关系，对于setXXXListener是1对1的关系，应该叫回调。
 */
public interface Subject {
    //注册
    public void registerObserver(Observer observer);

    //删除
    public void removeObserver(Observer observer);

    //通知
    public String notifyObserver(String msg);
}
