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
public class CategoryForm {
    @Size(max = 30)
    private String name;
    @Size(max = 255)
    private String category_image;

    public String getName() {
        return name;
    }

    public String getCategory_image() {
        return category_image;
    }
    
}
