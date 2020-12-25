package home.java8.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2020, xxx Corporation Limited.
 * @Date: 2020/12/25 上午 09:36
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("/int/stream")
public class IntStreamController {
    private final Logger logger = LoggerFactory.getLogger(IntStreamController.class);


    @RequestMapping(value = "/lambda")
    public String lambdaExcutor(String key, String operate) {
//        IntStream.range(10, 20).forEach((e) -> {
//            logger.info(String.valueOf(e));
//        });

//        OptionalInt optionalInt = IntStream.range(10, 20).reduce((u, o)->{
//           return u+o;
//        });
//        logger.info(optionalInt.getAsInt()+"");

//        int[] nums = {1,2,3,4,5,6,7,8,9};
//        double  average =  Arrays.stream(nums).average().getAsDouble();
//        logger.info(average+"");
//
//
//        double  averageBuilder = IntStream.builder().add(1).add(2).add(3).build().average().getAsDouble();
//        logger.info(averageBuilder+"");

        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Arrays.stream(nums).peek((o) -> {
            logger.info(o + "");
        });
        logger.info(Arrays.stream(nums).findFirst().getAsInt() + "");
        return "";
    }
}
