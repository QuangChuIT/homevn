package com.home.app.service.newspaper;

import com.home.app.model.Newspaper;
import com.home.app.repository.newspaper.NewspaperRepository;
import com.home.app.repository.newspaper.NoSuchNewspaperException;
import com.home.app.service.counter.CounterLocalServiceUtil;
import com.home.app.service.kernel.log.Log;
import com.home.app.service.kernel.log.LogFactoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewspaperLocalServiceImpl implements NewspaperLocalService {

    @Override
    public Newspaper insert(long id, String name, String alias) throws NoSuchNewspaperException {
        try {
            Newspaper newspaper = new Newspaper();
            if (id == 0) {
                id = CounterLocalServiceUtil.increment(Newspaper.class.getName());
                newspaper.setId(id);
            } else {
                newspaper = newspaperRepository.find(id);
            }

            newspaper.setName(name);
            newspaper.setAlias(alias);
            newspaperRepository.save(newspaper);
            return newspaper;
        } catch (NoSuchNewspaperException ex) {
            _log.error(ex);
            return null;
        }
    }

    @Override
    public Newspaper find(long newspaperId) throws NoSuchNewspaperException {
        return newspaperRepository.find(newspaperId);
    }

    @Override
    public List<Newspaper> findAll() throws NoSuchNewspaperException {
        return newspaperRepository.findAll();
    }

    @Override
    public List<Newspaper> findAll(Sort sort, int skip, int limit) throws NoSuchNewspaperException {
        return newspaperRepository.findAll(sort, skip, limit);
    }

    @Override
    public void remove(long newspaperId) throws NoSuchNewspaperException {
        newspaperRepository.remove(newspaperId);
    }

    @Override
    public long count(Criteria criteria) throws NoSuchNewspaperException {
        return newspaperRepository.count(criteria);
    }

    @Override
    public Newspaper findByAlias(String alias) throws NoSuchNewspaperException {
        return newspaperRepository.findByAlias(alias);
    }

    @Override
    public List<Newspaper> findByName(String name) throws NoSuchNewspaperException {
        try {
            Criteria criteria = Criteria.where("name").regex(name);
            return newspaperRepository.findAll(criteria);
        } catch (NoSuchNewspaperException nse) {
            throw new NoSuchNewspaperException();
        }
    }

    @Override
    public Page<Newspaper> findAll(Criteria criteria, Pageable pageable, Sort sort) throws NoSuchNewspaperException {
        return newspaperRepository.findAll(pageable, sort, criteria);
    }

    @Autowired
    public NewspaperLocalServiceImpl(NewspaperRepository newspaperRepository) {
        this.newspaperRepository = newspaperRepository;
    }

    private NewspaperRepository newspaperRepository;

    private final static Log _log = LogFactoryUtil.getLog(NewspaperLocalServiceImpl.class);
}
