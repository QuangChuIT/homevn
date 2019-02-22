package com.home.app.controller;

import com.google.common.collect.Lists;
import com.home.app.model.*;
import com.home.app.repository.link.LinkException;
import com.home.app.service.category.CategoryLocalService;
import com.home.app.service.kernel.json.JSONArray;
import com.home.app.service.kernel.json.JSONFactoryUtil;
import com.home.app.service.kernel.json.JSONObject;
import com.home.app.service.kernel.log.Log;
import com.home.app.service.kernel.log.LogFactoryUtil;
import com.home.app.service.kernel.util.StringUtil;
import com.home.app.service.link.LinkConstant;
import com.home.app.service.link.LinkService;
import com.home.app.service.newspaper.NewspaperLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.*;

@Controller
@RequestMapping("/quan-tri-link")
public class LinkController {

    @Autowired
    public LinkController(LinkService linkService, NewspaperLocalService newspaperLocalService,
                          CategoryLocalService categoryLocalService,
                          CategoryLocalService categoryService) {
        this.linkService = linkService;
        this.newspaperLocalService = newspaperLocalService;
        this.categoryLocalService = categoryLocalService;
        this.categoryService = categoryService;
    }

    @GetMapping("/addMenu")
    public String addMenu() {
        String filePath = "/home/quangcv/python/FilmCrawl/menu.txt";
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader buff = new BufferedReader(fileReader);
            String line = "";
            while ((line = buff.readLine()) != null) {
                Category category = categoryLocalService.find(1);
                String domain = StringUtil.getDomainName(line.trim());
                Property property = new Property("basic", new ArrayList<Extra>(), "p", "");
                Link link = linkService.updateLink(0, line.trim(), new ArrayList<String>(), category, property, 10, domain, 1000, 1);
                System.out.println(link);
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return "linkList";
    }

    @PostMapping("/updateStatus")
    public String updateStatus(@RequestBody String data) {
        try {
            JSONObject jsonObject = JSONFactoryUtil.createJSONObject(data);
            JSONArray linkIds = jsonObject.getJSONArray("linkIds");
            for (int i = 0; i < linkIds.length(); i++) {
                long linkId = Long.valueOf(linkIds.getString(i));
                Link link = linkService.findOne(linkId);
                int status = link.getActive();
                if (status == 0) {
                    status = 1;
                } else {
                    status = 0;
                }
                link.setActive(status);
                Link newLink = linkService.updateLink(link.getLinkId(), link.getUrl(), link.getCategoriesCrawl(), link.getCategory(), link.getProperty(), link.getTimeCrawl(), link.getDomain(), link.getChannelId(), status);
                System.out.println(newLink);
            }
        } catch (Exception e) {
            _log.error(e);
        }
        return "200";
    }

    @GetMapping("/updateAll")
    public String updateObject() throws SQLException, URISyntaxException {
        List<Link> links = linkService.findAll(new Sort(Sort.Direction.ASC, "id_"),
                -1, -1);
        for (Link item : links) {
            String url = item.getUrl();
            String domain = item.getDomain();
            if ("www.phimmoi.net".equals(domain)) {
                Link link = linkService.updateLink(item.getLinkId(), url, item.getCategoriesCrawl(), item.getCategory(), item.getProperty(), item.getTimeCrawl(), domain, item.getChannelId(), 1);
                System.out.println(link);
            } else {
                Link link = linkService.updateLink(item.getLinkId(), url, item.getCategoriesCrawl(), item.getCategory(), item.getProperty(), item.getTimeCrawl(), domain, item.getChannelId(), 0);
                System.out.println(link);
            }
            /*Property p = item.getProperty();
            JSONObject jsonObject = JSONFactoryUtil.createJSONObject(p.getxPath());
            String value = jsonObject.getString("url");
            try{
                JSONObject jsonObject1 = JSONFactoryUtil.createJSONObject(value);
                if (jsonObject1.has("url")) {
                    value = jsonObject1.getString("url");
                    JSONObject jsonObject2 = JSONFactoryUtil.createJSONObject(value);
                    if(jsonObject2.has("url")){
                        value = jsonObject2.getString("url");
                    }
                }
                JSONObject json = JSONFactoryUtil.createJSONObject();
                json.put("url", value);
                p.setxPath(json.toString());*/

        }
        /*int count = 0;
        while (resultSet.next()) {
            String url = resultSet.getString("Url").trim();
            String root = resultSet.getString("RootProperties");
            String xPath = resultSet.getString("LinkNews");
            long categoryId = resultSet.getLong("CategoryID");
            long channelId = resultSet.getLong("ChannelID");
            String path = "";
            if (!xPath.contains(root)) {
                path = root + xPath;
            } else {
                path = xPath;
            }
            System.out.println("Url :" + url + ", Xpath:" + path);
            Property property = new Property("basic", null, "p", path);
            Category category = categoryLocalService.find(categoryId);
            Link link = linkService.updateLink(0, url, null, category, property, 10, 1, channelId);
            count++;
        }
        System.out.println(count);*/
        return "linkList";
    }

    @GetMapping("/danh-sach")
    public String linkList(@RequestParam(value = "page", required = false) Integer page, Model model) {
        if (page == null) {
            page = 0;
        }
        Pageable pageable = new PageRequest(page, 20);
        Page<Link> page1 = linkService.findAll(pageable, new Sort(Sort.Direction.DESC, "linkId"));
        List<Link> links = Lists.newArrayList(page1);
        model.addAttribute("links", links);
        model.addAttribute("total", linkService.count());
        model.addAttribute("page", page);
        return "linkList";
    }

    @GetMapping("/tim-kiem")
    public String searchLink(@RequestParam("keyword") String keyWord, Model model) {
        List<Link> links = linkService.search(keyWord);
        model.addAttribute("links", links);
        return "linkList";
    }

    @ResponseBody
    @RequestMapping(value = "/xoa-link", method = RequestMethod.POST)
    public String deleteLink(@RequestBody String data) {
        try {
            JSONObject dataOb = JSONFactoryUtil.createJSONObject(data);
            JSONArray linkIds = dataOb.getJSONArray("linkIds");
            for (int i = 0; i < linkIds.length(); i++) {
                long linkId = Long.valueOf(linkIds.getString(i));
                linkService.deleteLink(linkId);
            }
            return "200";
        } catch (LinkException lex) {
            throw new LinkException("Error when delete link with info " + data);
        }
    }

    @GetMapping("/{category}/{linkId}")
    public String detail(@PathVariable("category") String category, @PathVariable("linkId") Long linkId, Model model) {
        try {
            System.out.println();
            Link link = linkService.findBy_C_L(category, linkId);
            if (link == null) {
                return "error";
            }
            model.addAttribute("link", link);
            List<Newspaper> newspapers = newspaperLocalService.findAll(new Sort(Sort.Direction.ASC, "name"), -1, -1);
            List<Category> categories = categoryService.findAll(new Sort(Sort.Direction.ASC, "name"), -1, -1);
            model.addAttribute("newspapers", newspapers);
            model.addAttribute("categories", categories);
            return "linkDetail";
        } catch (LinkException lne) {
            throw new LinkException("Error when get link ", lne);
        }

    }

    @GetMapping("/them-link")
    public String addLink(Model model) {
        List<Category> categories = categoryService.findAll(new Sort(Sort.Direction.DESC, "id"), -1, -1);
        model.addAttribute("categories", categories);
        return "addLink";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/update-link", consumes = "application/json;charset=utf-8", produces = "application/json;charset=utf-8")
    public String updateLink(@RequestBody String data) throws URISyntaxException {
        System.out.println("Getting update link via api!");
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject(data);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        long cateId = 2;
        System.out.println("Size : " + jsonArray.length());
        Category category = categoryLocalService.find(cateId);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            String url = object.getString("url").trim();
            long channelId = Long.valueOf(object.getString("channelId"));
            String xpath = object.getString("prop");
            List<Extra> extras = new ArrayList<>();
            String typeCrawl = LinkConstant.CRAWL_BY_BASIC;
            String tagCrawl = "p";
            String domain = StringUtil.getDomainName(url);
            Property property = new Property(typeCrawl,extras,tagCrawl,xpath);
            List<String> cateCrawls = new ArrayList<>();
            Link link = linkService.updateLink(0,url,cateCrawls,category,property,10,domain,channelId,1);
        }
        return "Success";
    }

    @ResponseBody
    @RequestMapping(value = "/them-moi-link", method = RequestMethod.POST)
    public String doAddLink(@RequestBody String data) throws URISyntaxException {
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject(data);
        String url = jsonObject.getString("url");
        Long linkId = Long.valueOf(jsonObject.getString("linkId"));
        /*long newId = Long.valueOf(jsonObject.getString("newspaper"));*/
        long cateId = Long.valueOf(jsonObject.getString("category"));
        int timeCrawl = Integer.valueOf(jsonObject.getString("timeCrawl"));
        String cateCrawl = jsonObject.getString("cateCrawl");
        int type = Integer.valueOf(jsonObject.getString("type"));
        String tagCrawl = jsonObject.getString("tagCrawl");
        JSONArray extraDatas = jsonObject.getJSONArray("extras");
        JSONObject error = JSONFactoryUtil.createJSONObject();
        List<String> cateCrawls = new ArrayList<>();
        String[] arrCate = cateCrawl.split(",");
        int active = Integer.valueOf(jsonObject.getString("active"));
        Collections.addAll(cateCrawls, arrCate);
        String xPath = jsonObject.getString("xPath");
        long channelId = Long.valueOf(jsonObject.getString("channelId"));
        /*Newspaper newspaper = newspaperLocalService.find(newId);*/
       /* if (newspaper == null) {
            error.put("err", "You must select newspaper!");
            return error.toString();
        }*/
        Category category = categoryLocalService.find(cateId);
        if (category == null) {
            error.put("err", "You must choose category!");
            return error.toString();
        }
        List<Extra> extras = new ArrayList<>();
        String typeCrawl = "";
        if (type == 1) {
            typeCrawl = LinkConstant.CRAWL_BY_CSS;
            for (int i = 0; i < extraDatas.length(); i++) {
                String extraData = extraDatas.getString(i);
                String[] extraArr = extraData.split("&");
                long extraId = Long.valueOf(extraArr[0]);
                String tag = extraArr[1];
                String clazz = extraArr[2];
                Extra extra = new Extra(extraId, tag, clazz);
                extras.add(extra);
            }
        } else {
            typeCrawl = LinkConstant.CRAWL_BY_BASIC;
        }
        if (type == 1) {
            if (extras.size() <= 0) {
                error.put("err", "Css property empty!");
                return error.toString();
            }
        }
        if (tagCrawl.equals("")) {
            tagCrawl = "p";
        }
        String domain = StringUtil.getDomainName(url);
        Property property = new Property(typeCrawl, extras, tagCrawl, xPath);
        if (linkId > 0) {
            Link link = linkService.updateLink(linkId, url, cateCrawls, category, property, timeCrawl, domain, channelId, active);
           /* Criteria criteria = Criteria.where("domain").is(domain);
            List<Link> linksByDomain = linkService.findAll(criteria);
            for (Link item : linksByDomain) {
                if (item.getLinkId() != link.getLinkId()) {
                    Property prop = item.getProperty();
                    prop.setxPath(xPath);
                    linkService.updateLink(item.getLinkId(), item.getUrl(), item.getCategoriesCrawl(), item.getCategory(), prop, item.getTimeCrawl(), item.getDomain(), item.getChannelId());
                }
            }*/
            return link.toString();
        } else {
            Link link = linkService.updateLink(0, url, cateCrawls, category, property, timeCrawl, domain, channelId, active);
            return link.toString();
        }
    }

    private LinkService linkService;
    private NewspaperLocalService newspaperLocalService;
    private CategoryLocalService categoryLocalService;
    private CategoryLocalService categoryService;
    private static final Log _log = LogFactoryUtil.getLog(LinkController.class);
}
