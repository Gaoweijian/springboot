package home.transaction.dao;

import home.transaction.dao.client.IAcountDao;
import home.transaction.dto.UAccount;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository(value = "acountDao")
class AcountImpl implements IAcountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UAccount getAcount(String name) {
        List<UAccount> acountList = jdbcTemplate.query("select * from u_acount where name=?", new BeanPropertyRowMapper<UAccount>(UAccount.class), name);
        if (acountList != null) {
            if (acountList.size() > 1) {
                throw new RuntimeException("查询异常,此名称账户存在多个.");
            } else {
                return acountList.get(0);
            }
        }
        throw new RuntimeException("查询异常");
    }

    public void updateAcount(UAccount account) {
        jdbcTemplate.update("update u_acount set money=? where id=?", +account.getMoney(), account.getId());
    }

    public void delAcount(int id) {
        jdbcTemplate.update("delete from u_acount where id=?", id);
    }
}
