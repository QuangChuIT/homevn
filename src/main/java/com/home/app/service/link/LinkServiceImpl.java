package com.home.app.service.link;

import com.home.app.model.Category;
import com.home.app.model.Link;
import com.home.app.model.Newspaper;
import com.home.app.model.Property;
import com.home.app.repository.link.LinkException;
import com.home.app.repository.link.LinkRepository;
import com.home.app.service.counter.CounterLocalServiceUtil;
import com.home.app.service.kernel.json.JSONFactoryUtil;
import com.home.app.service.kernel.json.JSONObject;
import com.home.app.service.kernel.log.Log;
import com.home.app.service.kernel.log.LogFactoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LinkServiceImpl implements LinkService {

    @Override
    public Link updateLink(long linkId, String url, List<String> cateCrawls,
                           Category category, Property property,
                           int timeCrawl, String domain, long channelId,
                           int active) throws LinkException {
        Link link = new Link();
        try {
            if (linkId == 0) {
                linkId = CounterLocalServiceUtil.increment(Link.class.getName());
                link.setLinkId(linkId);
            } else {
                link = linkRepository.findByPrimaryKey(linkId);
            }
            link.setUrl(url);
            link.setProperty(property);
            link.setTimeCrawl(timeCrawl);
            link.setCategory(category);
            link.setCategoriesCrawl(cateCrawls);
            link.setChannelId(channelId);
            link.setDomain(domain);
            link.setActive(active);
            linkRepository.save(link);
            return link;
        } catch (LinkException le) {
            throw new LinkException();
        }
    }

    @Override
    public void deleteLink(long lindId) throws LinkException {
        linkRepository.deleteLink(lindId);
    }

    @Override
    public List<Link> findAll(Sort sort, int skip, int limit) throws LinkException {
        return linkRepository.findAll(sort, skip, limit);
    }

    @Override
    public Page<Link> findAll(Pageable pageable, Sort sort) throws LinkException {
        return linkRepository.findAll(pageable, sort);
    }

    @Override
    public List<Link> findAll(Criteria criteria) throws LinkException {
        return linkRepository.findByCriteria(criteria);
    }


    @Override
    public List<Link> search(String keyword) throws LinkException {
        Criteria criteria = null;
        try {
            List<Link> links = new ArrayList<>();
            if (keyword != null && !keyword.equals("")) {
                criteria = new Criteria();
                criteria.orOperator(Criteria.where("url").regex(keyword, "i"), Criteria.where("category.name").regex(keyword, "i"));
                links = linkRepository.findByCriteria(criteria);
            }
            return links;
        } catch (Exception e) {
            throw new LinkException();
        }
    }

    @Override
    public long count() throws LinkException {
        return linkRepository.count();
    }

    @Override
    public Link findOne(long linkId) throws LinkException {
        return linkRepository.findByPrimaryKey(linkId);
    }

    @Override
    public Link findBy_C_L(String cateAlias, long linkId) throws LinkException {
        BasicQuery basicQuery = null;
        try {
            JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
            jsonObject.put("category.alias", cateAlias);
            jsonObject.put("linkId", linkId);
            basicQuery = new BasicQuery(jsonObject.toString());
            Link link = linkRepository.findByBasicQuery(basicQuery);
            if (link == null) {
                throw new LinkException("Not found link with category " + cateAlias + " and linkId " + linkId);
            }
            return link;
        } catch (LinkException lne) {
            throw new LinkException("error when execute query " + (basicQuery != null ? basicQuery.toString() : ""));
        }
    }

    @Override
    public List<Link> findByCategory(long categoryId) throws LinkException {
        try {
            Criteria criteria = Criteria.where("category.id").is(categoryId);
            return linkRepository.findByCriteria(criteria);
        } catch (LinkException lne) {
            throw new LinkException();
        }
    }

    @Override
    public boolean checkExistUrl(String url) throws LinkException {
        boolean isExist = false;
        try {
            Link link = linkRepository.findByUrl(url);
            if (link != null) {
                isExist = true;
            }
        } catch (LinkException le) {
            _log.error(le);
        }
        return isExist;
    }

    @Autowired
    private LinkRepository linkRepository;
    private final static Log _log = LogFactoryUtil.getLog(LinkServiceImpl.class);
}
