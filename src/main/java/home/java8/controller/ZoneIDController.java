package home.java8.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/1/15 下午 03:59
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping(value = "/zone/controller")
public class ZoneIDController {


    private final Logger logger = LoggerFactory.getLogger(ZoneIDController.class);

    /**
     * @描述
     * @参数 [value]
     * @返回值 java.lang.String
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/15
     * @修改人和其它信息
     */
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String testZonePost(String value) {
        ZoneId shZoneId = ZoneId.of("GMT");
        ZoneId.systemDefault();

        return value;
    }

    /**
     * @描述
     * @参数 [value]
     * @返回值 java.lang.String
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/15
     * @修改人和其它信息
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testZoneGet(String value) {

        return value;
    }
}
