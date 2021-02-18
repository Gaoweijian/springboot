package home.transaction.dao.client;

import home.transaction.dto.UAccount;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IAcountDaoManager {

    @Select("select * from u_acount where name=#{name}")
    public UAccount getAcount(String name);

    @Update("update u_acount set money=#{money},name=#{name} where id=#{id}")
    public boolean updateAcount(UAccount account);

    @Delete("delete from u_acount where id=#{id}")
    public boolean delAcount(int id);

    @Select("select * from u_acount")
    List<UAccount> getAccountList();

    @Insert("insert into u_acount (id,name,money,content) values (#{id},#{name},#{money},'mybatis-多租户测试')")
    public boolean saveAccount(UAccount account);

    @Select("select * from u_acount t left join u_tenant t2 on t2.id=t.id")
    List<UAccount> getCascadeAccountList();
}
