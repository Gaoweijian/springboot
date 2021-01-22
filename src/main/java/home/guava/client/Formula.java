package home.guava.client;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/1/22 下午 04:48
 * @Version: 1.0
 * @Description:函数式接口编程
 */
@FunctionalInterface
public interface Formula {

    /**
     * @描述 计算
     * @参数 [a]
     * @返回值 double
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/22
     * @修改人
     */
    double calculate(int a);

    /**
     * @描述 平方计算
     * @参数 [a]
     * @返回值 double
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/22
     * @修改人
     */
    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
