package com.home.app.repository.category;

import com.home.app.model.Category;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public interface CategoryRepository {
    Category updateCategory(Category category) throws CategoryException;

    Category findCategory(long categoryId) throws CategoryException;

    void removeCategory(long categoryId) throws CategoryException;

    Category fetchCategory(long categoryId) throws CategoryException;

    List<Category> findAll() throws CategoryException;

    List<Category> findAll(Sort sort, int skip, int limit) throws CategoryException;

    Category findByAlias(String alias) throws CategoryException;

    boolean checkExist(String alias) throws CategoryException;

    List<Category> findByCategoryIds(ArrayList<Long> categoryIds) throws CategoryException;

}
