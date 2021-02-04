package home.design.prototype.service;

import home.design.prototype.entity.Circle;
import home.design.prototype.entity.Rectangle;
import home.design.prototype.entity.Shape;
import home.design.prototype.entity.Square;

import java.util.Hashtable;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/2/4 上午 10:14
 * @Version: 1.0
 * @Description:
 */
public class ShapeCacheService {

    private static Hashtable<String, Shape> shapeMap = new Hashtable<>();


    /**
     * @描述
     * @参数 [shapeId]
     * @返回值 home.design.prototype.entity.Shape
     * @创建人 gao侧耳倾听
     * @创建时间 2021/2/4
     * @修改人
     */
    public static Shape getShape(String shapeId) {
        Shape cachedShape = shapeMap.get(shapeId);
        return (Shape) cachedShape.clone();
    }


    // 对每种形状都运行数据库查询，并创建该形状
    // shapeMap.put(shapeKey, shape);
    // 例如，我们要添加三种形状
    public static void loadCache() {
        Circle circle = new Circle();
        circle.setId("1");
        shapeMap.put(circle.getId(), circle);

        Square square = new Square();
        square.setId("2");
        shapeMap.put(square.getId(), square);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("3");
        shapeMap.put(rectangle.getId(), rectangle);
    }

}
