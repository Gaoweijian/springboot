/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.design.observe.service.client;

/**
 * @author wb-gwj607956
 * @version $Id: Subject.java, v 0.1 2020年12月22日 13:35 wb-gwj607956 Exp $
 */
public interface Subject {
    //注册
    public void registerObserver(Observer observer);

    //删除
    public void removeObserver(Observer observer);

    //通知
    public String notifyObserver(String msg);
}
