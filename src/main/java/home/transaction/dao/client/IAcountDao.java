package home.transaction.dao.client;

import home.transaction.dto.UAccount;

public interface IAcountDao {
    public UAccount getAcount(String name);

    public boolean updateAcount(UAccount account);

    public boolean delAcount(int id);
}
