package home.guava.po;

import lombok.Data;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/1/22 下午 05:47
 * @Version: 1.0
 * @Description:
 */
@Data
public class Person {
    /**
     * 姓
     */
    private String firstName;
    /**
     * 名
     */
    private String lastName;

    /**
     * 构造器
     *
     * @param firstName
     * @param lastName
     */
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * @描述
     * @参数
     * @返回值
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/23
     * @修改人
     */
    public Person() {
    }
}
