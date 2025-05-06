package com.ck.Service;

import com.ck.models.Categories;
import com.ck.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Categories saveCategory(Categories categories) {
        return this.categoryRepository.save(categories);
    }

    @Override
    public Categories getCategoryById(long id) {
        Optional<Categories> optional = categoryRepository.findById(id);
        Categories categories = null;
        if(optional.isPresent()) {
            categories = optional.get();
        }else {
            throw new RuntimeException("Category not found for id :: " + id);
        }
        return categories;
    }

    @Override
    public void deleteCategoryById(long id) {
        this.categoryRepository.deleteById(id);
    }

    @Override
    public List<Categories> getAllCategory() {
        return this.categoryRepository.findAll();
    }
}
