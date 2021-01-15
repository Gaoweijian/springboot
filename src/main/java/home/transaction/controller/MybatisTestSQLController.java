package home.transaction.controller;

import com.alibaba.fastjson.JSON;
import home.transaction.dao.client.IAcountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/1/12 下午 06:51
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping(value = "/home/transaction/mybatis")
public class MybatisTestSQLController {

    private final Logger logger = LoggerFactory.getLogger(MybatisTestSQLController.class);

    @Autowired
    private IAcountMapper iAcountMapper;

    //查询用户
    @RequestMapping(value = "/find/user")
    public String findUser(String name) {
        return JSON.toJSONString(iAcountMapper.getAcount(name));
    }

    //查询用户
    @RequestMapping(value = "/find/user/by")
    public String findUserByNameAndId(String name, int id) {
        return JSON.toJSONString(iAcountMapper.getAcountByNameAndId(id, name));
    }

}
