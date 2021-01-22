package home.guava.controller;

import home.guava.client.Formula;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/1/22 下午 04:13
 * @Version: 1.0
 * @Description:练习使用guava工具类
 */
@RestController
@RequestMapping(value = "guava")
public class GuavaController {
    /**
     * 日志打印
     */
    private final Logger logger = LoggerFactory.getLogger(GuavaController.class);

    @GetMapping("validate")
    public void validateGuava() {
        formulaMethod();


    }

    /**
     * @描述
     * @参数 []
     * @返回值 void
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/22
     * @修改人
     */
    private void expressions() {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, (o1, o2) -> {
            return 0;
        });
    }

    /**
     * @描述
     * @参数 []
     * @返回值 void
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/22
     * @修改人
     */
    private void formulaMethod() {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a);
            }
        };
        logger.info("[练习使用guava工具类]formula={}", formula.calculate(100));
        logger.info("[练习使用guava工具类]formula={}", formula.sqrt(16));
    }
}
