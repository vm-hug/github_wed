package com.ck.controllers;

import com.ck.Service.CategoryService;
import com.ck.models.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryController  {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String listCategories(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        return "categories";
    }

    @GetMapping("/showNewCategory")
    public String showNewCategory(Model model) {
        Categories categories = new Categories();
        model.addAttribute("category" , categories);
        return "Add_category";
    }

    @PostMapping("/saveCategory")
    public String saveCategory(@ModelAttribute("category") Categories category){
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/editCategory/{id}")
    public String editCategory(@PathVariable(value = "id") long id , Model model){
        Categories categories = categoryService.getCategoryById(id);
        model.addAttribute("category" , categories);
        return "Update_category";
    }

    @GetMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable(value ="id") long id){
        this.categoryService.deleteCategoryById(id);
        return "redirect:/categories";
    }

}
