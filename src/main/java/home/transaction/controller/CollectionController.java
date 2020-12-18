package home.transaction.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

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
        ArrayResource resource = new ArrayResource();
        for (int i = 0; i < 5; i++) {
            new Thread(resource, "" + i).start();
        }
        String result = JSON.toJSONString(resource.arrayList);
        System.out.println(Thread.currentThread().getName() + "\t" + result);
        return result;
    }


    /**
     * @描述
     * @参数 []
     * @返回值 java.lang.String
     * @创建人 gao侧耳倾听
     * @创建时间 2020/12/18
     * @修改人和其它信息
     */
    @RequestMapping(value = "/validate/set")
    public String validateSet() {

        SetResource resource = new SetResource();
        for (int i = 0; i < 5; i++) {
            new Thread(resource, "" + i).start();
        }
        String result = JSON.toJSONString(resource.set);
        System.out.println(Thread.currentThread().getName() + "\t" + result);
        return result;
    }

    class ArrayResource implements Runnable {

        //        private  List<String> arrayList = new ArrayList<>();
//        private volatile List<String> arrayList = new ArrayList<>();
//        private  List<String> arrayList = Collections.synchronizedList(new ArrayList<>());
        private List<String> arrayList = new CopyOnWriteArrayList();

        @Override
        public void run() {
            arrayList.add(UUID.randomUUID().toString().substring(0, 4));
            System.out.println(arrayList);
        }
    }

    class SetResource implements Runnable {
        private Set set = new HashSet();

        @Override
        public void run() {
            set.add(UUID.randomUUID().toString().substring(0, 4));
            System.out.println(set);
        }
    }

}
