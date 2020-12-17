package home.transaction.service;

import home.transaction.dao.client.IAcountDao;
import home.transaction.dto.UAccount;
import home.transaction.service.client.IAcountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "acountService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
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
}
