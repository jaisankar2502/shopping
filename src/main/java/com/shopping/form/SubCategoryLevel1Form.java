/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.form;

import javax.validation.constraints.Size;

/**
 *
 * @author albinps
 */
public class SubCategoryLevel1Form {
    @Size(max = 30)
    private String name;
    @Size(max = 255)
    private String sub_category_image;
    private Integer categoryId;

    public String getName() {
        return name;
    }

    public String getSub_category_image() {
        return sub_category_image;
    }

    public Integer getCategoryId() {
        return categoryId;
    }
    
}
