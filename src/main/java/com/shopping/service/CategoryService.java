/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.service;

import com.shopping.form.CategoryForm;
import com.shopping.view.CartItemView;
import com.shopping.view.CategoryView;
import com.shopping.view.SubCategoryLevel1View;
import java.util.List;

/**
 *
 * @author muhmin
 */
public interface CategoryService {

    public CartItemView getCartItemView(int itemId);

    public List<CategoryView> getAllCategory();

    public CategoryView addCategory(CategoryForm form);


    public List<SubCategoryLevel1View> getAllSubCategory(int CategoryId);
    
}
