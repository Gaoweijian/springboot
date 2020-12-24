package home.java8.dto;

import lombok.Data;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2020, xxx Corporation Limited.
 * @Date: 2020/12/24 下午 02:41
 * @Version: 1.0
 * @Description:
 */
@Data
public class Message {

    private int code;
    private String msg;

    public Message(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
