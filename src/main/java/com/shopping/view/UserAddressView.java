/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.view;

import com.shopping.entity.Address;
import com.shopping.json.Json;
import java.util.Date;

/**
 *
 * @author ajmal
 */
public class UserAddressView {

    private Integer addressId;
    private String name;
    private String country;
    private String address;
    private String city;
    private String state;
    private String landmark;
    private String zip;
    private byte status;
    @Json.DateTimeFormat
    private Date updatedDate;
    @Json.DateTimeFormat
    private Date createdDate;
    private String phone;
    private String type;

    public UserAddressView() {
    }

    public UserAddressView(Address addr) {
        this.addressId = addr.getAddressId();
        this.name = addr.getName();
        this.country = addr.getCountry();
        this.address = addr.getAddress();
        this.city = addr.getCity();
        this.state = addr.getState();
        this.landmark = addr.getLandmark();
        this.zip = addr.getZip();
        this.status = addr.getStatus();
        this.createdDate = addr.getCreatedDate();
        this.updatedDate = addr.getUpdatedDate();
        this.phone = addr.getPhone();
        this.type=(addr.getType()!=null)?addr.getType():null;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
