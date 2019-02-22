package com.home.app.repository.account;

import com.home.app.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    @Override
    public Account findOne(Criteria criteria) throws AccountException {
        try {
            Query query = new Query();
            query.addCriteria(criteria);
            return mongoTemplate.findOne(query, Account.class);
        } catch (AccountException ace) {
            throw new AccountException("Error when find Account");
        }
    }

    @Autowired
    private MongoTemplate mongoTemplate;
}
