package com.ck.Service;

import com.ck.models.Categories;

import java.util.List;

public interface CategoryService {
    Categories saveCategory(Categories categories);
    Categories getCategoryById(long id);
    void deleteCategoryById(long id);
    List<Categories> getAllCategory();
}
