package com.home.app.controller;

import com.home.app.model.Category;
import com.home.app.service.category.CategoryLocalService;
import com.home.app.service.kernel.log.Log;
import com.home.app.service.kernel.log.LogFactoryUtil;
import com.home.app.service.kernel.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/quan-tri-chuyen-muc")
public class CategoryController {

    private final CategoryLocalService categoryLocalService;

    @Autowired
    public CategoryController(CategoryLocalService categoryLocalService) {
        this.categoryLocalService = categoryLocalService;
    }

    @GetMapping()
    public String categoryIndex(Model model) {
        List<Category> categories = categoryLocalService.findAll(new Sort(Sort.Direction.DESC, "id"), -1, -1);
        model.addAttribute("categories", categories);
        return "category";
    }

    @GetMapping("/them-chuyen-muc")
    public String addCategory(Model model) {
        List<Category> categories = categoryLocalService.findAll();
        model.addAttribute("categories", categories);
        return "addCategory";
    }

    @GetMapping("/{alias}")
    public String detail(@PathVariable("alias") String alias, Model model) {
        Category category = categoryLocalService.findByAlias(alias);
        if (category != null) {
            model.addAttribute("category", category);
            return "detailCate";
        }
        return "error";
    }

    @RequestMapping(value = "/xoa-chuyen-muc/{cateId}", method = RequestMethod.GET)
    public String deleteCategory(@PathVariable("cateId") Long cateId, RedirectAttributes redirectAttributes) {
        categoryLocalService.delete(cateId);
        return "redirect:/quan-tri-chuyen-muc";
    }

    @RequestMapping(value = "/them-moi", method = RequestMethod.POST)
    public String doAddCategory(@RequestParam("name") String name, @RequestParam(value = "tags") String tags, Model model) {
        if (!name.equals("")) {
            String alias = StringUtil.toPrettyURL(name);
            if (categoryLocalService.checkExist(alias)) {
                List<Category> childCate = new ArrayList<>();
                childCate = getChild(tags);
                categoryLocalService.addCategory(0, name, alias, childCate);

            } else {
                model.addAttribute("message", "Chuyên mục đã tồn tại");
                return "addCategory";
            }
        } else {
            model.addAttribute("message", "Tên chuyên mục không được bỏ trống");
            return "addCategory";
        }
        return "redirect:/quan-tri-chuyen-muc";
    }

    @RequestMapping(value = "/cap-nhat")
    public String doUpdate(@RequestParam("cateId") Long cateId, @RequestParam("name") String name, @RequestParam("tags") String tags, Model model) {
        if (cateId == null) {
            return "error";
        }
        Category category = categoryLocalService.find(cateId);
        if (category == null) {
            return "error";
        }
        if (name == null || name.equals("")) {
            model.addAttribute("message", "Tên chuyên mục không được bỏ trống");
            model.addAttribute("category", category);
            return "detailCate";
        }
        String alias = StringUtil.toPrettyURL(name);
        List<Category> child = getChild(tags);
        categoryLocalService.addCategory(cateId, name, alias, child);
        return "redirect:/quan-tri-chuyen-muc";
    }

    private List<Category> getChild(String tags) {
        List<Category> childCate = new ArrayList<>();
        if (tags != null && !tags.equals("")) {
            String[] arrCate = tags.split(",");
            for (String item : arrCate) {
                String cateAlias = StringUtil.toPrettyURL(item);
                Category category = categoryLocalService.findByAlias(cateAlias);
                if (category != null) {
                    childCate.add(category);
                } else {
                    if (categoryLocalService.checkExist(cateAlias)) {
                        category = categoryLocalService.addCategory(0, item, cateAlias, null);
                        childCate.add(category);
                    }
                }
            }
        }
        return childCate;
    }

    private static final Log _log = LogFactoryUtil.getLog(CategoryController.class);
}
