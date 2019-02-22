package com.home.app.service.account;

import com.home.app.model.Account;
import com.home.app.repository.account.AccountException;
import com.home.app.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public Account findByUserName(String userName) throws AccountException {
        Criteria criteria = Criteria.where("userName").is(userName);
        return accountRepository.findOne(criteria);
    }

    @Autowired
    private AccountRepository accountRepository;
}
