package com.home.app.service.account;

import com.home.app.model.Account;
import com.home.app.repository.account.AccountException;

public interface AccountService {
    Account findByUserName(String userName) throws AccountException;
}
