/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.entity;

import com.shopping.form.SubCategoryLevel1Form;
import com.shopping.form.SubCategoryLevel2Form;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author muhmin
 */
@Entity
public class SubCatLevel1 {

    public static enum Status {
        DELETED((byte) 0),
        ACTIVE((byte) 1);

        public final byte value;

        private Status(byte value) {
            this.value = value;
        }
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subCatLevel1Id;
    private String name;
    private String subCatLevel1Image;
    private byte status;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Category category;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    public SubCatLevel1() {
    }
    SubCatLevel1(Integer subCatLevel1Id) {
      this.subCatLevel1Id=subCatLevel1Id;
    }
     public SubCatLevel1(SubCategoryLevel1Form form) {
        this.name=form.getName();
       this.subCatLevel1Image=form.getSub_category_image();
       this.category=new Category(form.getCategoryId());
       this.status=Category.Status.ACTIVE.value;
        Date dt = new Date();
        this.createdDate = dt;
        this.updatedDate = dt;
       }

    public Integer getSubcategoryId() {
        return subCatLevel1Id;
    }

    public void setSubcategoryId(Integer subcategoryId) {
        this.subCatLevel1Id = subcategoryId;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public String getSubCatLevel1Image() {
        return subCatLevel1Image;
    }

    public void setSubCatLevel1Image(String subCatLevel1Image) {
        this.subCatLevel1Image = subCatLevel1Image;
    }

    
    
    
}
