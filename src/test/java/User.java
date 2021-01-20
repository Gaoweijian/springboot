import lombok.Data;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/1/19 下午 08:55
 * @Version: 1.0
 * @Description:
 */
@Data
public class User {
    String id;
    String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
