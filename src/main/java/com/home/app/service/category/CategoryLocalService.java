package com.home.app.service.category;

import com.home.app.model.Category;
import com.home.app.repository.category.CategoryException;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public interface CategoryLocalService {
    Category addCategory(long id, String name, String alias, List<Category> childs) throws CategoryException;

    Category find(long categoryId) throws CategoryException;

    Category update(Category category) throws CategoryException;

    void delete(long categoryId) throws CategoryException;

    List<Category> findAll() throws CategoryException;

    List<Category> findAll(Sort sort, int skip, int limit) throws CategoryException;

    Category findByAlias(String alias) throws CategoryException;

    boolean checkExist(String alias) throws CategoryException;

    List<Category> findByCategoryIds(ArrayList<Long> categoryIds) throws CategoryException;
}
