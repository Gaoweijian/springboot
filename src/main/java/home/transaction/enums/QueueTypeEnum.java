package home.transaction.enums;

import lombok.Getter;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2020, xxx Corporation Limited.
 * @Date: 2020/12/18 下午 10:17
 * @Version: 1.0
 * @Description:
 */
public enum QueueTypeEnum {
    /**
     * 阻塞队列
     */
    ARRAY_BLOCKING_QUEUE("ARRAY", "数组队列"),
    LINKED_BLOCKING_QUEUE("LINKED", "链表队列"),
    SYNCHRONOUS_QUEUE("SYNCHRONOUS", "同步队列"),;

    /**
     * 阻塞队列编号
     */
    @Getter
    public String code;
    /**
     * 队列描述
     */
    @Getter
    public String desc;

    QueueTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static QueueTypeEnum getIdentificationCode(String code) {
        for (QueueTypeEnum cardenum : values()) {
            if (cardenum.getCode().equalsIgnoreCase(code)) {
                return cardenum;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "QueueTypeEnum{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
