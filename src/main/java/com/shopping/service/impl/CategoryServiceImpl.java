/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.service.impl;

import com.shopping.entity.Category;
import com.shopping.entity.Item;
import com.shopping.entity.SubCatLevel1;
import com.shopping.exception.NotFoundException;
import com.shopping.form.CategoryForm;
import com.shopping.repository.CategoryRepository;
import com.shopping.repository.ItemRepository;
import com.shopping.service.CategoryService;
import com.shopping.view.CartItemView;
import com.shopping.view.CategoryView;
import com.shopping.view.SubCategoryLevel1View;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.shopping.repository.SubCategoryLevel1Repository;

/**
 *
 * @author mukmin
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    
    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
     @Autowired
    private SubCategoryLevel1Repository subCategoryRepository;
    
    @Override
    public CartItemView getCartItemView(int itemId) {
        
       return itemRepository.findByItemId(itemId)  
                .map((item) -> {
                    return new CartItemView(item);
                }).orElseThrow(NotFoundException::new);

    }

    @Override
    public List<CategoryView> getAllCategory() {
        return categoryRepository.findAllByStatus(Category.Status.ACTIVE.value).stream().map(x->new CategoryView(x)).collect(Collectors.toList());
    }
    @Override
     public CategoryView addCategory(CategoryForm form){
      return new CategoryView(categoryRepository.save(new Category(form)));
 }
    @Override
     public List<SubCategoryLevel1View> getAllSubCategory(int CategoryId){
        return subCategoryRepository.findAllByCategoryCategoryIdAndStatus(CategoryId,SubCatLevel1.Status.ACTIVE.value).stream().map(x -> new SubCategoryLevel1View(x)).collect(Collectors.toList());
     }
}
