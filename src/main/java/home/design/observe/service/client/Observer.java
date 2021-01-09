/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package home.design.observe.service.client;

import lombok.Data;

import java.util.Objects;

/**
 * @author wb-gwj607956
 * @version $Id: Observer.java, v 0.1 2020年12月22日 13:37 wb-gwj607956 Exp $
 */
@Data
public class Observer {

    String id;
    String name;
    String msg;

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof Observer)) { return false; }
        Observer observer = (Observer) o;
        return getId().equals(observer.getId()) &&
                getName().equals(observer.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    public void updateMsg(String msg){
        this.msg = msg;
    }
}
