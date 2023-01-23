/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.view;

import com.shopping.entity.Category;
import com.shopping.entity.Item;
import com.shopping.json.Json;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author muhmin
 */
public class CartItemView { 

    private Integer itemId;
    private String name;
    private Float MRP;
    private Float price;
    private String image1;
    private String image2;
    private String image3;
    private String description;
    private Integer stock;
    private byte status;
    private CategoryView category;
    private subCategoryView subCategory;
    @Json.DateTimeFormat
    private Date createDate;
    @Json.DateTimeFormat
    private Date updateDate;


    public CartItemView() {
    }

    public CartItemView(Item item) {
        this.itemId = item.getItemId();
        this.name = item.getName();
        this.MRP = item.getMrp();
        this.price = item.getPrice();
        this.image1 = item.getImage1();
        this.image2 = item.getImage2();
        this.image3 = item.getImage3();
        this.description = item.getDescription();
        this.stock = item.getStock();
        this.status = item.getStatus();
        this.category = new CategoryView(item);
        this.subCategory = new subCategoryView(item);
        this.createDate = item.getCreateDate();
        this.updateDate = item.getUpdateDate();
    }

    private class CategoryView {

        private final Integer categoryId;
        private final String categoryname;

        public CategoryView(Item item) {
            this.categoryId = item.getCategory().getCategoryId();
            this.categoryname = item.getCategory().getName();
        }

        public Integer getCategoryId() {
            return categoryId;
        }

        public String getCategoryname() {
            return categoryname;
        }

    }
    
    private class subCategoryView {

        private final Integer subCategoryId;
        private final String subCategoryname;

        public subCategoryView(Item item) {
            this.subCategoryId = item.getSubCatLevel1().getSubcategoryId();
            this.subCategoryname = item.getSubCatLevel1().getName();
        }

        public Integer getSubCategoryId() {
            return subCategoryId;
        }

        public String getSubCategoryname() {
            return subCategoryname;
        }


    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getMRP() {
        return MRP;
    }

    public void setMRP(Float MRP) {
        this.MRP = MRP;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public CategoryView getCategory() {
        return category;
    }

    public void setCategory(CategoryView category) {
        this.category = category;
    }

    public subCategoryView getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(subCategoryView subCategory) {
        this.subCategory = subCategory;
    }



}
