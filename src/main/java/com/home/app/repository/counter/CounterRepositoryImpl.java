package com.home.app.repository.counter;

import com.home.app.model.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


@Repository
public class CounterRepositoryImpl implements CounterRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void reset(String name, int size) throws NoSuchCounterException {
        try {
            Query query = new Query();
            Criteria criteria = Criteria.where("name").is(name);

            query.addCriteria(criteria);

            Counter counter = mongoTemplate.findOne(query, Counter.class);

            if (counter == null) {
                throw new NoSuchCounterException("Not found counter with name " + name);
            }

            counter.setCurrentId(size);
            mongoTemplate.save(counter);
        } catch (NoSuchCounterException nce) {
            throw new NoSuchCounterException();
        }
    }

    @Override
    public void rename(String oldName, String newName) throws NoSuchCounterException {
        try {
            Query query = new Query();
            Criteria criteria = Criteria.where("name").is(oldName);
            query.addCriteria(criteria);

            Counter counter = mongoTemplate.findOne(query, Counter.class);

            if (counter == null) {
                throw new NoSuchCounterException("Not found counter with name " + oldName);
            }

            counter.setName(newName);
            mongoTemplate.save(counter);
        } catch (NoSuchCounterException nce) {
            throw new NoSuchCounterException();
        }
    }

    @Override
    public long increment(String name, int size) throws NoSuchCounterException {
        Query query = new Query();
        Criteria criteria = Criteria.where("name").is(name);
        query.addCriteria(criteria);
        query.fields().include("currentId");
        try {
            Update update = new Update();
            update.inc("currentId", size);

            FindAndModifyOptions findAndModifyOptions = new FindAndModifyOptions();
            findAndModifyOptions.returnNew(true);

            Counter counter = mongoTemplate.findAndModify(query, update, findAndModifyOptions, Counter.class);

            if (counter == null) {
                throw new NoSuchCounterException("Not found or create counter with name " + name);
            }

            return counter.getCurrentId();
        } catch (NoSuchCounterException nce) {
            //try create counter
            Counter count = new Counter(name, 1);

            mongoTemplate.save(count);

            count = mongoTemplate.findOne(query, Counter.class);

            if (count == null) {
                throw new NoSuchCounterException();
            } else {
                return count.getCurrentId();
            }
        }
    }
}
