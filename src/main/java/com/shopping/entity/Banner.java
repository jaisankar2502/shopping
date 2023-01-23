/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.entity;

import com.shopping.exception.BadRequestException;
import com.shopping.form.BannerForm;
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
public class Banner {

    public static enum Type {
        OFFER((byte) 1),
        SELL((byte) 2),
        NEW((byte) 3);

        public final byte value;

        private Type(byte value) {
            this.value = value;
        }

    }

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
    private Integer bannerId;
    private String description;
    private String img;
    private byte type;
    private byte status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    public Banner() {
    }

    public Banner(BannerForm form) {
        this.description = form.getDescription();
        this.img = form.getImg();
        switch (form.getType()) {
            case 1:
                this.type = Banner.Type.OFFER.value;
                break;
            case 2:
                this.type = Banner.Type.SELL.value;
                break;
            case 3:
                this.type = Banner.Type.NEW.value;
                break;
            default:
                throw new BadRequestException("Invalid Banner Type");
        }
        this.status = Banner.Status.ACTIVE.value;
        this.createdDate = new Date();
        this.updatedDate = new Date();
    }

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
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
