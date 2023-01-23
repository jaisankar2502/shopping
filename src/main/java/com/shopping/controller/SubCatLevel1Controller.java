/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.controller;

import com.shopping.form.SubCategoryLevel1Form;
import com.shopping.service.SubCategoryService;
import com.shopping.view.SubCategoryLevel1View;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author albinps
 */
@RestController
@RequestMapping("/subcategory")
public class SubCatLevel1Controller {

    @Autowired
    SubCategoryService subcategoryService;

   
    @PostMapping("/level1")
    public SubCategoryLevel1View addCategorylevel1(@RequestBody @Valid SubCategoryLevel1Form form) {
        return subcategoryService.addCategorylevel1(form);

    }
    @GetMapping("/level1")
    public List<SubCategoryLevel1View> listSubCategory(){
        return subcategoryService.getSubcategoryList();
    }
}
