/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.controller;

import com.shopping.form.SubCategoryLevel1Form;
import com.shopping.form.SubCategoryLevel2Form;
import com.shopping.service.SubCategoryService;
import com.shopping.view.SubCategoryLevel1View;
import com.shopping.view.SubCategoryLevel2View;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SubCatLevel2Controller {

    @Autowired
    SubCategoryService subcategoryService;

   
    @PostMapping("/level2")
    public SubCategoryLevel2View addCategorylevel2(@RequestBody @Valid SubCategoryLevel2Form form) {
        return subcategoryService.addCategorylevel2(form);

    }
}
