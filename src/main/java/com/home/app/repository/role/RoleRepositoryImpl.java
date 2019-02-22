package com.home.app.repository.role;

import com.home.app.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    @Autowired
    public RoleRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Role findRole(Criteria criteria) throws RoleException {
        try {
            Query query = new Query();
            query.addCriteria(criteria);
            return mongoTemplate.findOne(query, Role.class);
        } catch (RoleException rex) {
            throw new RoleException("Error when find role");
        }
    }

    private final MongoTemplate mongoTemplate;
}
