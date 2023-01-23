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
public class SubCategoryLevel2Form {
    @Size(max = 30)
    private String name;
    @Size(max = 255)
    private String sub_category_level2_image;
    private Integer subcategorylevel1Id;

    public String getName() {
        return name;
    }

    public String getSub_category_level2_image() {
        return sub_category_level2_image;
    }

    public Integer getSubcategorylevel1Id() {
        return subcategorylevel1Id;
    }

   
  
    
}
