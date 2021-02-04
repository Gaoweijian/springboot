package home.design.prototype.entity;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/2/4 上午 10:11
 * @Version: 1.0
 * @Description:
 */
@Slf4j
public class Circle extends Shape {

    /**
     * @描述
     * @参数 []
     * @返回值
     * @创建人 gao侧耳倾听
     * @创建时间 2021/2/4
     * @修改人
     */
    public Circle() {
        type = "Circle";
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
    public void draw() {
        log.info("[原型模式]class=Circle,draw方法执行");
    }
}
