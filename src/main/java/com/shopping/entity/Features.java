/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ajmal
 */
@Entity
public class Features {
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
     private Integer featuresId;
     private String features_desc;
     private byte status;
     @Temporal(TemporalType.TIMESTAMP)
     private Date createdDate;
     @Temporal(TemporalType.TIMESTAMP)
     private Date updatedDate;

    public Features() {
    }      

    public Integer getFeaturesId() {
        return featuresId;
    }

    public void setFeaturesId(Integer featuresId) {
        this.featuresId = featuresId;
    }        

    public String getFeatures_desc() {
        return features_desc;
    }

    public void setFeatures_desc(String features_desc) {
        this.features_desc = features_desc;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
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
