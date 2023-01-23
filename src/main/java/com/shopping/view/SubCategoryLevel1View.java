/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.view;

import com.shopping.entity.Category;
import com.shopping.entity.SubCatLevel1;
import com.shopping.json.Json;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ajmal
 */
public class SubCategoryLevel1View {
    private Integer sub_cat_level1_id;
    private String name;
    private String image;
    private  String sub_cat_level1_name;
    private byte status;
    private  String category_name;
    private Integer categoryId;
    @Json.DateTimeFormat
    private Date createdDate;
    @Json.DateTimeFormat
    private Date updatedDate;

    public SubCategoryLevel1View() {
    }

    
    public SubCategoryLevel1View(SubCatLevel1 subCategory) {
        this.sub_cat_level1_id = subCategory.getSubcategoryId();
        this.name = subCategory.getName();
        this.image =subCategory.getSubCatLevel1Image();
        this.status = subCategory.getStatus();
        this.category_name=subCategory.getCategory().getName();
        this.sub_cat_level1_name=subCategory.getName();
        this.categoryId = subCategory.getCategory().getCategoryId();
        //this.category = new CategoryView(subCategory.getCategoryId());
        this.createdDate = subCategory.getCreatedDate();
        this.updatedDate = subCategory.getUpdatedDate();
    }

    
    public Integer getSub_cat_level1_id() {
        return sub_cat_level1_id;
    }

    public void setSub_cat_level1_id(Integer sub_cat_level1_id) {
        this.sub_cat_level1_id = sub_cat_level1_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getImage() {
        return image;
    }

    public String getSub_cat_level1_name() {
        return sub_cat_level1_name;
    }

    public String getCategory_name() {
        return category_name;
    }
    
    
    
}
