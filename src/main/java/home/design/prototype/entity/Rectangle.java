package home.design.prototype.entity;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/2/4 上午 10:05
 * @Version: 1.0
 * @Description:
 */
@Slf4j
public class Rectangle extends Shape {

    /**
     * @描述 构造器
     * @参数 []
     * @返回值
     * @创建人 gao侧耳倾听
     * @创建时间 2021/2/4
     * @修改人
     */
    public Rectangle() {
        type = "Rectangle";
    }

    /**
     * @描述
     * @参数 []
     * @返回值 void
     * @创建人 gao侧耳倾听
     * @创建时间 2021/2/4
     * @修改人
     */
    @Override
    void draw() {
        log.info("[原型模式]class=Rectangle,draw方法执行");
    }
}
