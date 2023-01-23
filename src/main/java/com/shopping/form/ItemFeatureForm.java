/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.form;

import com.shopping.entity.Information;
import java.util.Collection;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ajmal
 */
public class ItemFeatureForm {
    
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
    @NotBlank
    private String image2;
    
    private Integer stock;
    
    @NotBlank
    private String image3;
    @NotNull
    private Integer categoryId;
    @NotNull
    private Integer subCategoryLevel1Id;
    
    @NotNull
    private Integer subCategoryLevel2Id;
    
    private Float discount;
    
    private Collection<Integer> features;
    
    private InformationForm informations;
    
    private Integer itemUniqueId;
     @NotNull
    private Integer quantity_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getMrp() {
        return mrp;
    }

    public void setMrp(Float mrp) {
        this.mrp = mrp;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
    
    public Collection<Integer> getFeatures() {
        return features;
    }

    public void setFeatures(Collection<Integer> features) {
        this.features = features;
    }  

    public Integer getSubCategoryLevel1Id() {
        return subCategoryLevel1Id;
    }

    public void setSubCategoryLevel1Id(Integer subCategoryLevel1Id) {
        this.subCategoryLevel1Id = subCategoryLevel1Id;
    }

    public Integer getSubCategoryLevel2Id() {
        return subCategoryLevel2Id;
    }

    public void setSubCategoryLevel2Id(Integer subCategoryLevel2Id) {
        this.subCategoryLevel2Id = subCategoryLevel2Id;
    }
    
    

    public InformationForm getInformations() {
        return informations;
    }

    public void setInformations(InformationForm informations) {
        this.informations = informations;
    } 

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Integer getQuantity_id() {
        return quantity_id;
    }

    public Integer getItemUniqueId() {
        return itemUniqueId;
    }

  

}
