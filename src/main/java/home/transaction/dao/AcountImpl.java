package home.transaction.dao;

import home.transaction.dao.client.IAcountDao;
import home.transaction.dto.UAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

    public boolean updateAcount(UAccount account) {
        int row = jdbcTemplate.update("update u_acount set money=?,name=? where id=?", account.getMoney(), account.getName(), account.getId());
        if (row == 1) {
            return true;
        }
        return false;
    }

    public boolean delAcount(int id) {
        int row = jdbcTemplate.update("delete from u_acount where id=?", id);
        if (row == 1) {
            return true;
        }
        return false;
    }
}
