package home.guava.client;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/1/22 下午 05:36
 * @Version: 1.0
 * @Description:
 */
@FunctionalInterface
public interface Converter<F, T> {

    /**
     * @描述 转换接口
     * @参数 [from]
     * @返回值 T
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/22
     * @修改人
     */
    T conver(F from);
}
