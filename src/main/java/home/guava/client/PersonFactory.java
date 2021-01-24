package home.guava.client;

import home.guava.po.Person;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/1/22 下午 05:49
 * @Version: 1.0
 * @Description:
 */
@FunctionalInterface
public interface PersonFactory<P extends Person> {

    /**
     * @描述 创建对象
     * @参数 [firstName, lastName]
     * @返回值 P
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/22
     * @修改人
     */
    P create(String firstName, String lastName);
}
