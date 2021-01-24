package home.guava.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import home.guava.client.Converter;
import home.guava.client.Formula;
import home.guava.client.PersonFactory;
import home.guava.po.Person;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
        //        formulaMethod();
        //        expressions();
        //        convertMethod();
        //        createMethod();
        //        optionalMethod();
        //        QueryWrapper wrapper;
        //        streamsMethod();
        //        mapsMethod();
        genericityMethod();

    }

    /**
     * @描述 泛型演习
     * @参数 []
     * @返回值 void
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/24
     * @修改人
     */
    private void genericityMethod() {

        Plate plate = new Plate<Apple>(new Apple("我是苹果"));
        plate.setItem(new Apple("我是苹果"));
        logger.info("[泛型演习]plate={}", plate.getItem());

        Plate<? super Fruit> plate1 = new Plate<>(new Apple("我是苹果"));
        plate1.setItem(new Fruit("我是水果"));
        logger.info("[泛型演习]plate1={}", plate1.getItem());

    }


    /**
     * @描述 maps
     * @参数 []
     * @返回值 void
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/24
     * @修改人
     */
    private void mapsMethod() {
        Map maps = new HashMap();
        maps.putIfAbsent("1", "1#value");
        logger.info("[maps操作演示]maps={}", maps);

    }

    /**
     * @描述 stream操作演习
     * @参数 []
     * @返回值 void
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/24
     * @修改人
     */
    private void streamsMethod() {
        List<String> strings = Arrays.asList("1", "2", "5", "3", "1");
        logger.info("[stream操作演习]strings={}", strings);

        List<String> filterStr = strings.stream().filter(o -> o.length() > 3).collect(Collectors.toList());
        logger.info("[stream操作演习]filterStr={}", filterStr);

        //sorted按照自然顺序升序排列
        List<String> sortedStr = strings.stream().sorted().collect(Collectors.toList());
        logger.info("[stream操作演习]sortedStr={}", sortedStr);

        Set<Integer> setStr = strings.stream().map(o -> Integer.valueOf(o)).collect(Collectors.toSet());
        logger.info("[stream操作演习]setStr={}", setStr);

        TreeSet<String> treeSetStr = strings.stream().collect(Collectors.toCollection(TreeSet::new));
        logger.info("[stream操作演习]treeSetStr={}", treeSetStr);

        Map<String, Integer> mapStr = strings.stream().collect(Collectors.toMap(o -> o, o -> Integer.valueOf(o) * Integer.valueOf(o), (o1, o2) -> Integer.valueOf(o1) * Integer.valueOf(o1)));
        logger.info("[stream操作演习]mapStr={}", mapStr);

        String joinStr = strings.stream().collect(Collectors.joining(","));
        logger.info("[stream操作演习]joinStr={}", joinStr);

        Integer sumStr = strings.stream().collect(Collectors.reducing(0, x -> Integer.valueOf(x), (y, x) -> x + y));
        logger.info("[stream操作演习]sumStr={}", sumStr);

        String stringStr = strings.stream().collect(Collectors.reducing(
                "start",
                (x) -> {
                    logger.info("[stream操作演习]reducing,x={}", x);
                    return x;
                },
                (y, x) -> {
                    logger.info("[stream操作演习]reducing,x={},y={}", x, y);
                    return x + y;
                }));
        logger.info("[stream操作演习]stringStr={}", stringStr);

        boolean anyMatch = strings.stream().anyMatch(o -> {
            logger.info("[stream操作演习]anyMatch,o={}", o);
            return o.equalsIgnoreCase("2");
        });
        logger.info("[stream操作演习]anyMatch={}", anyMatch);

        boolean allMatch = strings.stream().allMatch(o -> {
            logger.info("[stream操作演习]allMatch,o={}", o);
            return Integer.valueOf(o) > 2;
        });
        logger.info("[stream操作演习]allMatch={}", allMatch);

        boolean noneMatch = strings.stream().noneMatch(o -> {
            logger.info("[stream操作演习]noneMatch,o={}", o);
            return Integer.valueOf(o) > 5;
        });
        logger.info("[stream操作演习]noneMatch={}", noneMatch);

        logger.info("[stream操作演习]count={}", strings.stream().count());

        Optional<String> reduces = strings.stream().sorted().reduce((o1, o2) -> {
            logger.info("[stream操作演习]reduce,o1={},o2={}", o1, o2);
            return o1 + o2;
        });
        logger.info("[stream操作演习]reduces={}", reduces.get());


    }

    /**
     * @描述 optional类操作
     * @参数 []
     * @返回值 void
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/23
     * @修改人
     */
    private void optionalMethod() {

        Optional<Person> person = Optional.ofNullable(new Person("zhao", "yu"));
        logger.info("[optional类操作]person={},{}", person.isPresent(), person.get());

        Optional<Person> person2 = Optional.ofNullable(new Person());
        logger.info("[optional类操作]person2={},{}", person2.isPresent(), person2);

        person.flatMap(u -> Optional.ofNullable(u.getLastName()));

    }

    /**
     * @描述 四大函数式编程接口
     * @参数 []
     * @返回值 void
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/23
     * @修改人
     */
    private void functionMethod() {
        //有入参返回boolean
        Predicate<String> predicate = (o) -> {
            JSONObject object = JSON.parseObject(o);
            return StringUtils.isEmpty(object.get("key"));
        };
        //有入参有出参
        Function<String, Integer> function = Integer::valueOf;
        //无入参有出参
        Supplier supplier = () -> {
            return 0;
        };
        //有入参无出参
        Consumer consumer = (o) -> {
        };
    }


    /**
     * @描述 创建对象
     * @参数 []
     * @返回值 void
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/22
     * @修改人
     */
    private void createMethod() {
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("gao", "weijian");
        logger.info("[练习使用guava工具类]person={}", person);
    }

    /**
     * @描述 对象转换
     * @参数 []
     * @返回值 void
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/22
     * @修改人
     */
    private void convertMethod() {
        Converter<String, Integer> converter = Integer::valueOf;
        logger.info("[练习使用guava工具类]convert={}", converter.conver("1254") instanceof Integer);
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
        //集合类排序
        Collections.sort(names, (o1, o2) -> o2.compareTo(o1));
        names.stream().forEach((o) -> {
            logger.info("[练习使用guava工具类]name={}", o);
        });
        logger.info("********************************");
        //使用List的排序功能
        names.sort((o1, o2) -> o2.compareTo(o1));
        names.forEach(o -> logger.info("[练习使用guava工具类]name={}", o));
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


/**
 * 泛型类
 */
class Owner<T> {
    public T operateMethod(T paramer) {
        return paramer;
    }

    /**
     * 泛型方法
     *
     * @param vue
     * @param <V>
     */
    public <V> void operateV(V vue) {

    }
}

@Data
class Fruit {
    private String name;

    public Fruit(String name) {
        this.name = name;
    }
}

class Apple extends Fruit {
    public Apple(String name) {
        super(name);
    }
}

class Plate<T extends Fruit> {
    private T item;

    public Plate(T t) {
        item = t;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T t) {
        item = t;
    }
}