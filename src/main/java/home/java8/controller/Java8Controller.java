/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.java8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author wb-gwj607956
 * @version $Id: controller.java, v 0.1 2020年12月23日 9:57 wb-gwj607956 Exp $
 */
@Controller
@ResponseBody
@RequestMapping(value = "/java8")
public class Java8Controller {

    private final Logger logger = LoggerFactory.getLogger(Java8Controller.class);

    @RequestMapping(value = "/lambda")
    public String lambdaExcutor(String key, String operate) {
        java8Proccess(key, (e) -> {

            switch (operate) {
                case "optional":
                    optionalMethod(e);
                    break;
                case "stream":
                    streamMethod(e);
                    break;
                default:
                    break;
            }
        });
        return "";
    }

    private void streamMethod(String e) {
        List<Message> list = Arrays.asList(
                new Message(1, "消息1"),
                new Message(2, "消息2"),
                new Message(3, "消息3"),
                new Message(4, "消息4"),
                new Message(5, "消息5"),
                new Message(6, "消息6"),
                new Message(7, "消息7"),
                new Message(8, "消息8"),
                new Message(9, "消息9"),
                new Message(0, "消息0")
        );
        list.stream().filter(o -> o.getCode() > 5).forEach((o) -> {
            logger.info(o.toString());
        });

        list.stream().sorted((u, o) -> Integer.compare(u.getCode(), o.getCode()));
    }

    private void optionalMethod(String e) {
        Optional<String> optional = Optional.ofNullable(e);
        logger.info(String.valueOf(optional.isPresent()));
        e = null;
        logger.info(String.valueOf(optional.isPresent()));
        optional.orElse("重新赋值");
        logger.info(String.valueOf(optional.isPresent()));

        System.out.println(optional.flatMap((value) -> {
            logger.info(value + "/flatMap");
            return Optional.of(value.toUpperCase());
        }));

        //Message message = new Message();
        //Optional<Message> optionalMessage = Optional.ofNullable(message);
        //logger.info(String.valueOf(optionalMessage.isPresent()));
        //optionalMessage = null;
        //logger.info(String.valueOf(optionalMessage.isPresent()));
        //optionalMessage.orElse(new Message());
        //logger.info(String.valueOf(optionalMessage.isPresent()));
    }

    public void java8Proccess(String key, Consumer<String> consumer) {
        consumer.accept(key);
    }

    public void java8Proccess(Message message, Consumer<Message> consumer) {
        consumer.accept(message);
    }

}
