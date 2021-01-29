package home.token;

import java.lang.annotation.*;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/1/28 上午 10:50
 * @Version: 1.0
 * @Description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Token {
    boolean save() default false;

    boolean remove() default false;
}
