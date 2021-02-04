package home.design.factory.service.impl;

import home.design.factory.service.RouJiaMo;
import org.springframework.stereotype.Service;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/2/3 下午 04:52
 * @Version: 1.0
 * @Description:
 */
@Service(value = "suanRouJiaMoService")
public class SuanRouJiaMoServiceImpl extends AbstractRouJiaMoService implements RouJiaMo {

    /**
     * @描述 酸味肉夹馍
     * @参数 [rouJiaMoName]
     * @返回值
     * @创建人 gao侧耳倾听
     * @创建时间 2021/2/3
     * @修改人
     */
    public SuanRouJiaMoServiceImpl() {
        this.rouJiaMoName = "酸味肉夹馍";
    }
}
