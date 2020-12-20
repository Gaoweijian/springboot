package home.transaction.dao.client;

import home.transaction.dto.UAccount;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
}
