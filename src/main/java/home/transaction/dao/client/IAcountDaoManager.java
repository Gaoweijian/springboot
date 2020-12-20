package home.transaction.dao.client;

import home.transaction.dto.UAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IAcountDaoManager {

    @Select("select * from u_acount where name=#{name}")
    public UAccount getAcount(String name);

    public boolean updateAcount(UAccount account);

    public boolean delAcount(int id);
}
