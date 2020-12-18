package home.transaction.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wb-gwj607956
 * @version $Id: CASController.java, v 0.1 2020年12月18日 16:51 wb-gwj607956 Exp $
 * <p>
 * CAS compare and set 比较并交换
 */
@RestController
@RequestMapping(value = "/cas")
public class CASController {

    @RequestMapping(value = "/vaildate")
    public String casVaildate(int initNum, int updateNum) {
        Thread thread = Thread.currentThread();
        StringBuilder sb = new StringBuilder();
        AtomicInteger atomic = new AtomicInteger(initNum);
        sb.append("initNum：" + initNum + " updateNum：" + updateNum);
        sb.append("     ");
        sb.append(thread.getName() + "-" + atomic.compareAndSet(initNum, updateNum));
        sb.append("     ");
        sb.append(thread.getName() + "-" + atomic.compareAndSet(initNum, updateNum));
        System.out.println(JSON.toJSONString(sb));
        return JSON.toJSONString(sb);
    }
}
