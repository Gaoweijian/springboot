package home.transaction.controller;

import home.transaction.dao.client.IAcountDaoManager;
import home.transaction.dto.UAccount;
import home.transaction.service.client.IAcountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2020/12/18, xxx Corporation Limited.
 * @Contact: xxx@xxx.com
 * @Date: 2020/12/18 下午 08:52
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping(value = "home/transaction")
public class RequestController {

    private final Logger logger = LoggerFactory.getLogger(RequestController.class);

    @Autowired
    private IAcountService acountService;


    @Autowired
    IAcountDaoManager iAcountDaoManager;

    /**
     * @描述
     * @参数 []
     * @返回值 boolean
     * @创建人 gao侧耳倾听
     * @创建时间 2020/12/18
     * @修改人和其它信息
     */
    @RequestMapping(value = "/connection")
    public boolean startConnection() {
        Thread thread = Thread.currentThread();
        logger.trace("客户端连接成功");
        return acountService.transationAcount("张三", "李四", 1);
    }

    @RequestMapping(value = "/ccount")
    public UAccount getUAccountByName(String name) {
        return iAcountDaoManager.getAcount(name);
    }
}
