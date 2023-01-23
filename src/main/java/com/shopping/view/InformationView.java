/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.view;

import com.shopping.entity.Information;
import com.shopping.json.Json;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ajmal
 */
public class InformationView {
    private Integer informationId;
    private String brand;
    private String manufacturer;
    private String country;
    private byte status;
    @Json.DateTimeFormat
    private Date createDate;
    @Json.DateTimeFormat
    private Date updateDate;

    public InformationView() {
    }

    public InformationView(Information info) {
        this.informationId = info.getInformationId();
        this.brand = info.getBrand();
        this.manufacturer = info.getManufacturer();
        this.country = info.getCountry();
        this.status = info.getStatus();
        this.createDate = info.getCreatedDate();
        this.updateDate = info.getUpdatedDate();
    }

    
    public Integer getInformationId() {
        return informationId;
    }

    public void setInformationId(Integer informationId) {
        this.informationId = informationId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
    
    
    
}
