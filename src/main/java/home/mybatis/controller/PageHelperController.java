package home.mybatis.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import home.transaction.dao.client.IAcountMapper;
import home.transaction.dto.UAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/1/12 下午 08:23
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping(value = "/mybatis/controller")
public class PageHelperController {

    private final Logger logger = LoggerFactory.getLogger(PageHelperController.class);

    @Autowired
    private IAcountMapper iAcountMapper;

    //查询用户
    @RequestMapping(value = "/find/user")
    public String findUserByNameAndId(Integer pageNum, Integer pageSize) {

        //分页
        Page<UAccount> pageInfo = PageHelper.startPage(pageNum, pageSize);

        return JSON.toJSONString(iAcountMapper.findAcountList());
    }

}
