package home.transaction.service;

import home.transaction.dao.client.IAcountDao;
import home.transaction.dto.UAccount;
import home.transaction.service.client.IAcountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service(value = "acountService")
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AcountServiceImpl implements IAcountService {

    @Autowired
    IAcountDao acountDao;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean transationAcount(String acountA, String acountB, int money) {
        UAccount accountA = acountDao.getAcount(acountA);
        UAccount accountB = acountDao.getAcount(acountB);
        if (accountA != null && accountB != null) {
            accountA.setMoney(accountA.getMoney() - money);
            acountDao.updateAcount(accountA);

//            int i = 1 / 0;

            accountB.setMoney(accountB.getMoney() + money);
            acountDao.updateAcount(accountB);
            System.out.println(acountA + "\t向 " + acountB + " 转账成功：" + money);
            return true;
        } else {
            System.out.println("用户数据不存在");
            return false;
        }
    }

    @Override
    public boolean updateAccount(UAccount account) {
        return acountDao.updateAcount(account);
    }

    /**
     * @描述
     * @参数 []
     * @返回值 void
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/28
     * @修改人
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testTransactional() {
        log.info("[事务测试]stemp={}", 1);
        try {
//            updateAcount();
            log.info("[事务测试]stemp={}", 2);
            saveAcount();
            log.info("[事务测试]stemp={}", 3);
        } catch (Exception e) {
            log.error("[事务测试]Exception={}", e.getMessage());
        }
        log.info("[事务测试]end={}", "close");
    }

    /**
     * @描述
     * @参数 []
     * @返回值 void
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/28
     * @修改人
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateAcount() {
        log.info("[事务测试]updateAcount={}", 1);
        UAccount accountA = acountDao.getAcount("张三");
        accountA.setMoney(20);
        log.info("[事务测试]updateAcount={}", 2);
        acountDao.updateAcount(accountA);
        log.info("[事务测试]updateAcount={}", 3);
    }


    /**
     * @描述
     * @参数 []
     * @返回值 void
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/28
     * @修改人
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveAcount() {
        try {
            log.info("[事务测试]saveAcount={}", 1);
            UAccount accountA = acountDao.getAcount("李四");
            accountA.setMoney(30);
            log.info("[事务测试]saveAcount={}", 2);
            int i = 1 / 0;
            acountDao.updateAcount(accountA);
            log.info("[事务测试]saveAcount={}", 3);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("[事务测试]saveAcount={}", 4);
        }

//        log.info("[事务测试]saveAcount={}", 1);
//        UAccount accountA = acountDao.getAcount("李四");
//        accountA.setMoney(30);
//        log.info("[事务测试]saveAcount={}", 2);
//        int i = 1 / 0;
//        acountDao.updateAcount(accountA);
//        log.info("[事务测试]saveAcount={}", 3);
    }


    /**
     * @描述
     * @参数 []
     * @返回值 void
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/28
     * @修改人
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testTransactional2() {
        log.info("[事务测试]stemp={}", 1);
        updateAcount();
        log.info("[事务测试]stemp={}", 2);
        saveAcount();
        log.info("[事务测试]stemp={}", 3);
    }
}
