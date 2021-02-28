package home.transaction.controller;

import home.transaction.dao.client.IAcountDaoManager;
import home.transaction.dao.client.IAcountMapper;
import home.transaction.dto.UAccount;
import home.transaction.service.CallableService;
import home.transaction.service.CommonVariable;
import home.transaction.service.client.IAcountService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private IAcountMapper iAcountMapper;

    @Autowired
    private CommonVariable commonVariable;

    @RequestMapping(value = "async")
    public void tryAsync() {
        log.info("springboot异步方法测试,START");
        log.info("[commonVariable]A,result={}", commonVariable.tryAsyncA());
        log.info("[commonVariable]B,result={}", commonVariable.tryAsyncB());
        log.info("[commonVariable]C,result={}", commonVariable.tryAsyncC());
        log.info("springboot异步方法测试,END");
    }

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


    /**
     * mybatis-tenant 多租户测试
     */
    @PostMapping(value = "tenant")
    public void mybatisTenantTest(@RequestBody UAccount account) {


        //新增
        log.info("[mybatis-plus多租户测试]saveAccount,result={}", iAcountDaoManager.saveAccount(account));

        //修改
        account.setMoney(100);
        log.info("[mybatis-plus多租户测试]updateAcount,result={}", iAcountDaoManager.updateAcount(account));

        //查询
        UAccount uAccount = iAcountDaoManager.getAcount(account.getName());
        log.info("[mybatis-plus多租户测试]getAcount,uAccount={}", uAccount);

        //级联查询
        List<UAccount> accounts = iAcountDaoManager.getCascadeAccountList();
        log.info("[mybatis-plus多租户测试]getCascadeAccountList,accounts={}", accounts);

        //删除
        log.info("[mybatis-plus多租户测试]delAcount,result={}", iAcountDaoManager.delAcount(account.getId()));

//        过滤sql测试
        List<UAccount> accountList = iAcountMapper.findAcountList();
        log.info("[mybatis-plus多租户测试]findAcountList,accountList={}", accountList);
    }

    @GetMapping(value = "countdownlatch")
    public Map countDownLatchTest() throws Exception {
        Map map = new HashMap();
        Long startTime = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(3);

        FutureTask task = new FutureTask(new CallableService(countDownLatch, () -> iAcountDaoManager
                .getAcount("张三"), map));
        new Thread(task).start();
        log.info("[逻辑1]耗时={} ms", System.currentTimeMillis() - startTime);

        FutureTask task2 = new FutureTask(new CallableService(countDownLatch, () -> iAcountDaoManager
                .getCascadeAccountList(), map));
        new Thread(task2).start();
        log.info("[逻辑2]耗时={} ms", System.currentTimeMillis() - startTime);

        FutureTask task3 = new FutureTask(new CallableService(countDownLatch, () -> iAcountMapper.findAcountList(), map));
        new Thread(task3).start();
        log.info("[逻辑3]耗时={} ms", System.currentTimeMillis() - startTime);

        countDownLatch.await();
        Long endTime = System.currentTimeMillis();
        map.put("耗时", (endTime - startTime) + " ms");
        return map;
    }

    @GetMapping(value = "no/countdownlatch")
    public Map noCountDownLatchTest() throws InterruptedException {
        Map map = new HashMap();
        Long startTime = System.currentTimeMillis();
        TimeUnit.SECONDS.sleep(2);
        map.put("acount", iAcountDaoManager.getAcount("张三"));
        TimeUnit.SECONDS.sleep(2);
        map.put("accounts", iAcountDaoManager.getCascadeAccountList());
        TimeUnit.SECONDS.sleep(2);
        map.put("accountList", iAcountMapper.findAcountList());
        Long endTime = System.currentTimeMillis();
        map.put("耗时", (endTime - startTime) + " ms");
        return map;
    }
}
