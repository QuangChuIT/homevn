package com.home.app.service.category;

import com.home.app.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryLocalServiceUtil {

    public static Category find(long categoryId){
        return getCategoryLocalService().find(categoryId);
    }

    @Autowired
    public void setCategoryLocalService(CategoryLocalService categoryLocalService) {
        this.categoryLocalService = categoryLocalService;
    }

    public static CategoryLocalService getCategoryLocalService() {
        return categoryLocalService;
    }

    private static CategoryLocalService categoryLocalService;
}
