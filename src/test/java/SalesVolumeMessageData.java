import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/1/29 下午 04:22
 * @Version: 1.0
 * @Description: 销售额报表数据(从BI大数据系统获取)
 */
@Data
public class SalesVolumeMessageData {
    /**
     * 城市编号
     */
    private String cityCode;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 当月客户下单总金额
     */
    private BigDecimal monthAmount;

    /**
     * 当月总达成率
     */
    private Double monthTurnoverRatio;

    /**
     * 当月预估总达成率
     */
    private Double estimateTurnoverRatio;

}
