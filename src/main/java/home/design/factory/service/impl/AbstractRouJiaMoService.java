package home.design.factory.service.impl;

import home.design.factory.service.RouJiaMo;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/2/3 下午 04:34
 * @Version: 1.0
 * @Description: 肉夹馍
 */
@Slf4j
abstract class AbstractRouJiaMoService implements RouJiaMo {
    /**
     * 肉夹馍名称
     */
    protected String rouJiaMoName;

    @Override
    public void prepare() {
        log.info("[肉夹馍制作]准备阶段(揉面-调菜),rouJiaMoName={}", rouJiaMoName);
    }

    @Override
    public void make() {
        log.info("[肉夹馍制作]制作阶段(用菜夹馍),rouJiaMoName={}", rouJiaMoName);
    }

    @Override
    public void unpack() {
        log.info("[肉夹馍制作]打包阶段(用袋子装好),rouJiaMoName={}", rouJiaMoName);
    }
}
