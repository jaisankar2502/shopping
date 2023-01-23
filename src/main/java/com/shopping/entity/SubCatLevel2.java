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
 * @author albinps
 */
@Entity
public class SubCatLevel2 {

    
     
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
    private Integer subCatLevel2Id;
    private String subCatLevel2Name;
    private String subCatLevel2Image;
    private byte status;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SubCatLevel1 subCatLevel1;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    public SubCatLevel2() {
    }
    SubCatLevel2(Integer subCatlevel2Id) {
      this.subCatLevel2Id=subCatlevel2Id;
    }
     public SubCatLevel2(SubCategoryLevel2Form form) {
        this.subCatLevel2Name=form.getName();
       this.subCatLevel2Image=form.getSub_category_level2_image();
       this.subCatLevel1=new SubCatLevel1(form.getSubcategorylevel1Id());
       this.status=Category.Status.ACTIVE.value;
        Date dt = new Date();
        this.createdDate = dt;
        this.updatedDate = dt;
       }

    public Integer getSubCatLevel2Id() {
        return subCatLevel2Id;
    }

    public void setSubCatLevel2Id(Integer subCatLevel2Id) {
        this.subCatLevel2Id = subCatLevel2Id;
    }

    public String getSubCatLevel2Name() {
        return subCatLevel2Name;
    }

    public void setSubCatLevel2Name(String subCatLevel2Name) {
        this.subCatLevel2Name = subCatLevel2Name;
    }

    public String getSubCatLevel2Image() {
        return subCatLevel2Image;
    }

    public void setSubCatLevel2Image(String subCatLevel2Image) {
        this.subCatLevel2Image = subCatLevel2Image;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public SubCatLevel1 getSubCatLevel1() {
        return subCatLevel1;
    }

    public void setSubCatLevel1(SubCatLevel1 subCatLevel1) {
        this.subCatLevel1 = subCatLevel1;
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
