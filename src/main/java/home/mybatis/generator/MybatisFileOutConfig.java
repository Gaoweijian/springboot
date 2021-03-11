package home.mybatis.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/3/11 下午 01:32
 * @Version: 1.0
 * @Description:
 */
@Data
@AllArgsConstructor
public class MybatisFileOutConfig extends FileOutConfig {

    /**
     * 项目地址
     */
    private String projectPath;

    /**
     * 全局配置
     */
    private PackageConfig pc;

    @Override
    public String outputFile(TableInfo tableInfo) {
        // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
        return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
    }
}
