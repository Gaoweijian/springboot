package home.transaction.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import home.transaction.service.client.IAcountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "home/transaction/")
public class RequestController {


    @Autowired
    private IAcountService acountService;

    @RequestMapping(value = "connection")
    public boolean startConnection() {
        Thread thread = Thread.currentThread();
        System.out.println("客户端连接成功:\t" + thread.getName());
        return acountService.transationAcount("张三","李四",1);
    }
}
