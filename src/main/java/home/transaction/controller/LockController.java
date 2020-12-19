package home.transaction.controller;

import home.transaction.dto.UAccount;
import home.transaction.service.client.IAcountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2020, xxx Corporation Limited.
 * @Date: 2020/12/19 上午 09:32
 * @Version: 1.0
 * @Description: 公平锁/非公平锁/可重入锁/递归锁/自旋锁 谈谈你的理解
 */
@RestController
@RequestMapping("/lock")
public class LockController {


    @Autowired
    private IAcountService acountService;

    /**
     * @描述
     * @参数 [account]
     * @返回值 java.lang.String
     * @创建人 gao侧耳倾听
     * @创建时间 2020/12/19
     * @修改人和其它信息
     */
    @RequestMapping("/update/account")
    public String updateSameAccount(@RequestBody UAccount account) throws ExecutionException, InterruptedException {
        Thread thread = Thread.currentThread();
        LockResource resource = new LockResource(account, acountService);
        FutureTask<Boolean> futureTask = new FutureTask<Boolean>(resource);
        new Thread(futureTask, "Lock-thread").start();
        System.out.println(thread.getName() + "\t" + futureTask.get() + "\t" + account.toString());
        return String.valueOf(futureTask.get());
    }


    class LockResource implements Callable<Boolean> {

        private IAcountService acountService;

        private UAccount account;

        public LockResource(UAccount account, IAcountService acountService) {
            this.account = account;
            this.acountService = acountService;
        }

        @Override
        public Boolean call() throws Exception {
            return acountService.updateAccount(account);
        }
    }
}
