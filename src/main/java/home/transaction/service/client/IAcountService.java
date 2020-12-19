package home.transaction.service.client;

import home.transaction.dto.UAccount;

public interface IAcountService {
    public boolean transationAcount(String acountA, String acountB, int money);

    boolean updateAccount(UAccount account);
}
