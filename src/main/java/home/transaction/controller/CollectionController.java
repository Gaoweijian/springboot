package home.transaction.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2020, xxx Corporation Limited.
 * @Contact: CollectionController
 * @Date: 2020/12/18 下午 08:32
 * @Version: 1.0
 * @Description: 我们知道ArrayList是线程不安全的，请编码写一个不安全的案例并给出解决方案。
 */
@ResponseBody
@Controller
@RequestMapping(value = "/collection")
public class CollectionController {


    /**
     * @描述
     * @参数 []
     * @返回值 java.lang.String
     * @创建人 gao侧耳倾听
     * @创建时间 2020/12/18
     * @修改人和其它信息
     */
    @RequestMapping(value = "/validate/array")
    public String validateArrayList() {
        CollectionResource resource = new CollectionResource();
        for (int i = 0; i < 5; i++) {
            new Thread(resource, "" + i).start();
        }
        String result = JSON.toJSONString(resource.arrayList);
        System.out.println(Thread.currentThread().getName() + "\t" + result);
        return result;
    }

    class CollectionResource implements Runnable {

        private List<String> arrayList = new ArrayList<>();

        @Override
        public void run() {
            arrayList.add(UUID.randomUUID().toString().substring(0, 8));
        }
    }
}
