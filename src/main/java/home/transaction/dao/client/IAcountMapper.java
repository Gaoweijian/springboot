package home.transaction.dao.client;

import com.baomidou.mybatisplus.annotation.SqlParser;
import home.transaction.dto.UAccount;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: gao侧耳倾听
 * @License: (C) Copyright 2005-2021, xxx Corporation Limited.
 * @Date: 2021/1/12 下午 06:43
 * @Version: 1.0
 * @Description:
 */
@Mapper
@Component
public interface IAcountMapper {

    /**
     * @描述
     * @参数 [name]
     * @返回值 home.transaction.dto.UAccount
     * @创建人 gao侧耳倾听
     * @创建时间 2021/2/19
     * @修改人
     */
    public UAccount getAcount(String name);

    /**
     * @描述
     * @参数 [account]
     * @返回值 boolean
     * @创建人 gao侧耳倾听
     * @创建时间 2021/2/19
     * @修改人
     */
    public boolean updateAcount(UAccount account);

    /**
     * @描述
     * @参数 [id]
     * @返回值 boolean
     * @创建人 gao侧耳倾听
     * @创建时间 2021/2/19
     * @修改人
     */
    public boolean delAcount(int id);

    /**
     * @描述
     * @参数 [id, name]
     * @返回值 home.transaction.dto.UAccount
     * @创建人 gao侧耳倾听
     * @创建时间 2021/2/19
     * @修改人
     */
    public UAccount getAcountByNameAndId(int id, String name);

    /**
     * @描述
     * @参数 []
     * @返回值 java.util.List<home.transaction.dto.UAccount>
     * @创建人 gao侧耳倾听
     * @创建时间 2021/2/19
     * @修改人
     */
    @SqlParser(filter = true)
    public List<UAccount> findAcountList();
}
