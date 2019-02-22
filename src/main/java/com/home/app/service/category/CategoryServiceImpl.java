package com.home.app.service.category;

import com.home.app.model.Category;
import com.home.app.model.Link;
import com.home.app.repository.category.CategoryException;
import com.home.app.repository.category.CategoryRepository;
import com.home.app.service.counter.CounterLocalServiceUtil;
import com.home.app.service.kernel.log.Log;
import com.home.app.service.kernel.log.LogFactoryUtil;
import com.home.app.service.link.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryLocalService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category addCategory(long id, String name, String alias, List<Category> childs) throws CategoryException {
        try {
            Category category = new Category();
            if (id == 0) {
                long categoryId = CounterLocalServiceUtil.increment(Category.class.getName());
                category.setId(categoryId);
            } else {
                category.setId(id);
            }
            category.setAlias(alias);
            category.setCategories(childs);
            category.setName(name);
            return categoryRepository.updateCategory(category);
        } catch (CategoryException cex) {
            _log.error(cex);
            return null;
        }
    }

    @Override
    public Category find(long categoryId) throws CategoryException {
        return categoryRepository.fetchCategory(categoryId);
    }

    @Override
    public Category update(Category category) throws CategoryException {
        return categoryRepository.updateCategory(category);
    }

    @Override
    public void delete(long categoryId) throws CategoryException {
        categoryRepository.removeCategory(categoryId);
    }

    @Override
    public List<Category> findAll() throws CategoryException {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findAll(Sort sort, int skip, int limit) throws CategoryException {
        return categoryRepository.findAll(sort, skip, limit);
    }

    @Override
    public Category findByAlias(String alias) throws CategoryException {
        return categoryRepository.findByAlias(alias);
    }

    @Override
    public boolean checkExist(String alias) throws CategoryException {
        return categoryRepository.checkExist(alias);
    }

    @Override
    public List<Category> findByCategoryIds(ArrayList<Long> categoryIds) throws CategoryException {
        return categoryRepository.findByCategoryIds(categoryIds);
    }

    private static Log _log = LogFactoryUtil.getLog(CategoryServiceImpl.class);
}
