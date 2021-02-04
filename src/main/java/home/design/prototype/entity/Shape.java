package home.design.prototype.entity;

import lombok.Data;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/2/4 上午 09:59
 * @Version: 1.0
 * @Description:
 */
@Data
public abstract class Shape implements Cloneable {

    private String id;
    protected String type;

    @Override
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    abstract void draw();
}
