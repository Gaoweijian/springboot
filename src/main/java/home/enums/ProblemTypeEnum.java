package home.enums;

import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/1/26 下午 02:21
 * @Version: 1.0
 * @Description: 题目类型枚举
 */
public enum ProblemTypeEnum {
    /**
     * 填空题
     */
    BLANK("BLANK", "填空题"),
    /**
     * 选择题
     */
    CHOICE("CHOICE", "选择题"),
    /**
     * 判断题
     */
    JUDGE("JUDGE", "判断题"),
    /**
     * 简答题
     */
    ANSWER("ANSWER", "简答题");

    @Getter
    public String code;
    @Getter
    public String desc;

    ProblemTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * @描述
     * @参数 [code]
     * @返回值 home.enums.ProblemTypeEnum
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/26
     * @修改人
     */
    public static ProblemTypeEnum getProblemTypeEnumByCode(String code) {

        for (ProblemTypeEnum typeEnum : values()) {
            if (typeEnum.code.equalsIgnoreCase(code)) {
                return typeEnum;
            }
        }
        return null;
    }

    /**
     * @描述
     * @参数 [code]
     * @返回值 home.enums.ProblemTypeEnum
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/26
     * @修改人
     */
    public static ProblemTypeEnum getProblemTypeEnumByDesc(String desc) {

        for (ProblemTypeEnum typeEnum : values()) {
            if (typeEnum.desc.equals(desc)) {
                return typeEnum;
            }
        }
        return null;
    }

    /**
     * @描述
     * @参数 [desc]
     * @返回值 home.enums.ProblemTypeEnum
     * @创建人 gao侧耳倾听
     * @创建时间 2021/1/26
     * @修改人
     */
    public static ProblemTypeEnum getProblemTypeEnumContainsDesc(String desc) {
        if (!StringUtils.isEmpty(desc)) {
            for (ProblemTypeEnum typeEnum : values()) {
                if (desc.contains(typeEnum.desc)) {
                    return typeEnum;
                }
            }
        }
        return null;
    }
}
