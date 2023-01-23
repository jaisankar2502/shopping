/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.controller;

import com.shopping.form.CategoryForm;
import com.shopping.service.CategoryService;
import com.shopping.view.CartItemView;
import com.shopping.view.CategoryView;
import com.shopping.view.SubCategoryLevel1View;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author albinps
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    CategoryService categoryService;
    
    @GetMapping("item/{itemId}")
    public CartItemView getCartItemView(@PathVariable("itemId") int itemId) {
        return categoryService.getCartItemView(itemId);
    }
    @GetMapping
    public List<CategoryView> getAllCategory(){
        return categoryService.getAllCategory();
    
    }
    @GetMapping("/{CategoryId}")
    public List<SubCategoryLevel1View> getAllSubCategory(@PathVariable("CategoryId") int CategoryId){
        return categoryService.getAllSubCategory(CategoryId);
    
    }
    
    @PostMapping
    public CategoryView addCategory(@RequestBody @Valid CategoryForm form){
        return categoryService.addCategory(form);  
    
    }
}
