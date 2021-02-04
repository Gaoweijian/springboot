package home.design.factory.client;

import home.design.factory.service.RouJiaMo;
import home.design.factory.service.impl.LaRouJiaMoServiceImpl;
import home.design.factory.service.impl.SuanRouJiaMoServiceImpl;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/2/3 下午 04:59
 * @Version: 1.0
 * @Description:
 */
public class SimpleRouJiaMoFactroy {

    /**
     * @描述
     * @参数 [type]
     * @返回值 home.design.factory.service.RouJiaMo
     * @创建人 gao侧耳倾听
     * @创建时间 2021/2/3
     * @修改人
     */
    public RouJiaMo createRouJiaMo(String type) {
        switch (type) {
            case "LA":
                return new LaRouJiaMoServiceImpl();
            case "SUAN":
                return new SuanRouJiaMoServiceImpl();
            default:
        }
        return null;
    }
}
