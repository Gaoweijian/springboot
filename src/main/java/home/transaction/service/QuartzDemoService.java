package home.transaction.service;

import home.transaction.dao.client.IAcountDao;
import home.transaction.dto.UAccount;
import home.transaction.service.client.IAcountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/1/11 下午 09:50
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@Service("quartzDemoService")
public class QuartzDemoService implements IAcountService {

    @Autowired
    IAcountDao acountDao;

    @Override
    public boolean transationAcount(String acountA, String acountB, int money) {
        return false;
    }

    @Override
    public boolean updateAccount(UAccount account) {
        return false;
    }

    @Override
    public void testTransactional() {

    }

    @Override
    public void testTransactional2() {

    }


    /**
     * @描述
     * @参数 []
     * @返回值 void
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/28
     * @修改人
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void saveAcount() {
        log.info("[事务测试]saveAcount={}", 1);
        UAccount accountA = acountDao.getAcount("李四");
        accountA.setMoney(30);
        log.info("[事务测试]saveAcount={}", 2);
        int i = 1 / 0;
        acountDao.updateAcount(accountA);
        log.info("[事务测试]saveAcount={}", 3);
    }
}
