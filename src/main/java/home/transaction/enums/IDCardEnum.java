package home.transaction.enums;

import lombok.Getter;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2020, xxx Corporation Limited.
 * @Date: 2020/12/18 下午 10:17
 * @Version: 1.0
 * @Description:
 */
public enum IDCardEnum {
    /**
     * 身份证
     */
    IDENTIFICATION_CODE("1", "身份证");

    /**
     * 证件编号
     */
    @Getter
    private String code;
    /**
     * 证件描述
     */
    private String desc;

    IDCardEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public IDCardEnum getIdentificationCode(String code) {
        for (IDCardEnum cardenum : values()) {
            if (cardenum.getCode().equalsIgnoreCase(code)) {
                return cardenum;
            }
        }
        return null;
    }
}
