package com.home.app.repository.account;

import com.home.app.model.Account;
import org.springframework.data.mongodb.core.query.Criteria;

public interface AccountRepository {
    Account findOne(Criteria criteria) throws AccountException;
}
