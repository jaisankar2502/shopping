/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.view;

import com.shopping.entity.SubCatLevel2;
import com.shopping.json.Json;
import java.util.Date;

/**
 *
 * @author ajmal
 */
public class SubCategoryLevel2View {
    private Integer subCategoryLevel2Id;
    private String name;
    private Integer subCategoryLevel1Id;
    private byte status;
    @Json.DateTimeFormat
    private Date createdDate;
    @Json.DateTimeFormat
    private Date updatedDate;

    public SubCategoryLevel2View() {
    }

    
    public SubCategoryLevel2View(SubCatLevel2 subCategorylevel2) {
        this.subCategoryLevel2Id = subCategorylevel2.getSubCatLevel2Id();
        this.name = subCategorylevel2.getSubCatLevel2Name();
        this.subCategoryLevel1Id=subCategorylevel2.getSubCatLevel1().getSubcategoryId();
        this.status = subCategorylevel2.getStatus();
        this.createdDate = subCategorylevel2.getCreatedDate();
        this.updatedDate = subCategorylevel2.getUpdatedDate();
    }

    
    public Integer getSubCategoryLevel2Id() {
        return subCategoryLevel2Id;
    }

    public void setSubCategoryLevel2Id(Integer subCategoryLevel2Id) {
        this.subCategoryLevel2Id = subCategoryLevel2Id;
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

    public Integer getSubCategoryLevel1Id() {
        return subCategoryLevel1Id;
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
    
    
    
}
