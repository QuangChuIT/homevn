package com.home.app.controller;

import com.google.common.collect.Lists;
import com.home.app.model.Newspaper;
import com.home.app.repository.newspaper.NoSuchNewspaperException;
import com.home.app.service.kernel.json.JSONArray;
import com.home.app.service.kernel.json.JSONFactoryUtil;
import com.home.app.service.kernel.json.JSONObject;
import com.home.app.service.kernel.log.Log;
import com.home.app.service.kernel.log.LogFactoryUtil;
import com.home.app.service.newspaper.NewspaperLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/quan-tri-bao")
public class NewController {

    @GetMapping("/api/danh-sach-bao")
    @ResponseBody
    public List<Newspaper> getNewspapers() {
        return newspaperLocalService.findAll(new Sort(Sort.Direction.ASC, "name"), -1, -1);
    }


    @GetMapping("/danh-sach-bao")
    public String getListNew(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (page == null) {
            page = 0;
        }
        Criteria criteria = null;
        if (keyword != null && !keyword.equals("")) {
            criteria = new Criteria();
            criteria.orOperator(Criteria.where("name").regex(keyword,"i"), Criteria.where("alias").regex(keyword,"i"));
        }
        long total = newspaperLocalService.count(criteria);
        Pageable pageable = new PageRequest(page, 8);
        Page<Newspaper> pageNews = newspaperLocalService
                .findAll(criteria, pageable, new Sort(Sort.Direction.DESC, "id"));
        List<Newspaper> newspapers = Lists.newArrayList(pageNews);
        model.addAttribute("newspapers", newspapers);
        model.addAttribute("page", page);
        model.addAttribute("total", total);
        model.addAttribute("keyword", keyword);
        return "newspaperList";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/them-bao")
    @ResponseBody
    public String addNewspaper(@RequestBody String data) {
        try {
            JSONObject jsonObject = JSONFactoryUtil.createJSONObject(data);
            String newName = jsonObject.getString("newName");
            String alias = jsonObject.getString("alias");
            Newspaper newspaper = newspaperLocalService.insert(0, newName, alias);
            return newspaper.toString();
        } catch (NoSuchNewspaperException nse) {
            throw new NoSuchNewspaperException("Error when execute add newspaper");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/xoa-bao")
    @ResponseBody
    public String deleteNew(@RequestBody String data) {
        try {
            JSONObject jsonObject = JSONFactoryUtil.createJSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("newIds");
            for (int i = 0; i < jsonArray.length(); i++) {
                long newId = Long.valueOf(jsonArray.getString(i));
                newspaperLocalService.remove(newId);
            }
            return "success";
        } catch (NoSuchNewspaperException nse) {
            throw new NoSuchNewspaperException("Error when delete newspaper");
        }
    }

    @Autowired
    public NewController(NewspaperLocalService newspaperLocalService) {
        this.newspaperLocalService = newspaperLocalService;
    }

    private NewspaperLocalService newspaperLocalService;
    private Log _log = LogFactoryUtil.getLog(NewController.class);
}
