package home.transaction.dao.client;

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

    public UAccount getAcount(String name);

    public boolean updateAcount(UAccount account);

    public boolean delAcount(int id);

    public UAccount getAcountByNameAndId(int id, String name);

    public List<UAccount> findAcountList();

}
