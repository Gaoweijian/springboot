package home.design.decorator.service;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/2/3 下午 05:39
 * @Version: 1.0
 * @Description:
 */
public interface IEquip {

    /**
     * @描述 计算攻击力
     * @参数 []
     * @返回值 int
     * @创建人 gao侧耳倾听
     * @创建时间 2021/2/3
     * @修改人
     */
    int caculateAttack();

    /**
     * @描述 装备的描述
     * @参数 []
     * @返回值 java.lang.String
     * @创建人 gao侧耳倾听
     * @创建时间 2021/2/3
     * @修改人
     */
    String description();
}
