package home.mybatis.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/3/11 上午 10:15
 * @Version: 1.0
 * @Description:
 */
@Data
@TableName(value = "u_acount")
public class UserAcount {

    /**
     * 主键id
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 用户名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 用户资产
     */
    @TableField(value = "money")
    private Integer money;

    /**
     * 内容说明
     */
    @TableField(value = "content")
    private String content;

    /**
     * 租户id
     */
    @TableField(value = "tenant_id")
    private String tenantId;
}
