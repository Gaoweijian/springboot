package home.transaction.dao.client;

import home.transaction.dto.UAccount;

public interface IAcountDao {
    public UAccount getAcount(String name);

    public void updateAcount(UAccount account);

    public void delAcount(int id);
}
