package home.design.factory.service;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/2/3 下午 04:36
 * @Version: 1.0
 * @Description: 肉夹馍接口
 */
public interface RouJiaMo {

    /**
     * 准备阶段
     */
    void prepare();

    /**
     * 夹馍制作
     */
    void make();

    /**
     * 打包阶段
     */
    void unpack();
}
