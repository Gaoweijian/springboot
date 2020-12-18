package home.transaction.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2020/12/18, xxx Corporation Limited.
 * @Contact: xxx@xxx.com
 * @Date: 2020/12/18 下午 08:51
 * @Version: 1.0
 * @Description: CAS compare and set 比较并交换
 */
@RestController
@RequestMapping(value = "/cas")
public class CASController {


    /**
     * @描述
     * @参数 [initNum, updateNum]
     * @返回值 java.lang.String
     * @创建人 gao侧耳倾听
     * @创建时间 2020/12/18
     * @修改人和其它信息
     */
    @RequestMapping(value = "/vaildate")
    public String casVaildate(int initNum, int updateNum) {
        Thread thread = Thread.currentThread();
        StringBuilder sb = new StringBuilder();
        AtomicInteger atomic = new AtomicInteger(initNum);
        sb.append("initNum：" + initNum + " updateNum：" + updateNum);
        sb.append("    ");
        sb.append(thread.getName() + "-" + atomic.compareAndSet(initNum, updateNum));
        sb.append("    ");
        sb.append(thread.getName() + "-" + atomic.compareAndSet(initNum, updateNum));
        System.out.println(JSON.toJSONString(sb));
        return JSON.toJSONString(sb);
    }
}
