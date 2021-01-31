package home.transaction.controller;

import home.transaction.dao.client.IAcountDaoManager;
import home.transaction.dto.UAccount;
import home.transaction.service.CommonVariable;
import home.transaction.service.client.IAcountService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2020/12/18, xxx Corporation Limited.
 * @Contact: xxx@xxx.com
 * @Date: 2020/12/18 下午 08:52
 * @Version: 1.0
 * @Description:
 */
@Slf4j
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
    @RequestMapping(value = "/connections", method = RequestMethod.GET)
    public boolean startConnection() {
        logger.trace("客户端连接成功");
        log.info("[事务测试]map={}", CommonVariable.map);
        return acountService.transationAcount("张三", "李四", 1);
    }

    /**
     * @描述
     * @参数 [name]
     * @返回值 home.transaction.dto.UAccount
     * @创建人 gao侧耳倾听
     * @创建时间 2020/12/20
     * @修改人和其它信息
     */
    @RequestMapping(value = "/ccount")
    public UAccount getUAccountByName(String name) {
        return iAcountDaoManager.getAcount(name);
    }

    /**
     * @描述
     * @参数 []
     * @返回值 boolean
     * @创建人 gao侧耳倾听
     * @创建时间 2020/12/18
     * @修改人和其它信息
     */
    @RequestMapping(value = "/connection", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean startConnection(String accountAName, String accountBName, int money) {
        Thread thread = Thread.currentThread();
        logger.trace("客户端连接成功");
        UAccount accountA = iAcountDaoManager.getAcount(accountAName);
        UAccount accountB = iAcountDaoManager.getAcount(accountBName);
        if (accountA != null && accountB != null) {
            accountA.setMoney(accountA.getMoney() - money);
            iAcountDaoManager.updateAcount(accountA);
            int i = 1 / 0;
            accountB.setMoney(accountB.getMoney() + money);
            iAcountDaoManager.updateAcount(accountB);
            logger.info(accountAName + "\t向 " + accountBName + " 转账成功：" + money);
            return true;
        } else {
            logger.info("用户数据不存在");
            return false;
        }
    }

    @PostMapping(value = "test/transactional")
    public void testTransactional() {
        logger.info("[testTransactional]START");
        acountService.testTransactional();
        logger.info("[testTransactional]END");
    }

    @PostMapping(value = "test/transactional2")
    public void testTransactional2() {
        logger.info("[testTransactional]START");
        acountService.testTransactional2();
        logger.info("[testTransactional]END");
    }

    @PostMapping("/propagation")
    public void transactionPropagation() {
        acountService.transactionPropagation();
    }
}
