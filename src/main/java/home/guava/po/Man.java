package home.guava.po;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/1/22 下午 05:53
 * @Version: 1.0
 * @Description:
 */
public class Man extends Person {

    /**
     * 用户id
     */
    @Getter
    @Setter
    private String uid;

    public Man(String firstName, String lastName, String uid) {
        super(firstName, lastName);
        this.uid = uid;
    }
}
