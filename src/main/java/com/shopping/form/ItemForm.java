/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author albinps
 */
public class ItemForm {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private Float mrp;
    @NotNull
    private Float price;
    @NotBlank
    private String image1;
   
    private String image2;
   
    private String image3;
    @NotNull
    private Integer categoryId;
    @NotNull
    private  Integer Stock;
    
    private Float discount;
    @NotNull
    private Integer quantity_id;
    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Float getPrice() {
        return price;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public String getImage1() {
        return image1;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Float getMrp() {
        return mrp;
    }

   

    public String getImage2() {
        return image2;
    }

    public String getImage3() {
        return image3;
    }

    public Integer getStock() {
        return Stock;
    }

    public Integer getQuantity_id() {
        return quantity_id;
    }

       
}
