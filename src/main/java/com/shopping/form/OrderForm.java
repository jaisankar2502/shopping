/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.form;

import java.util.Collection;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ajmal
 */
public class OrderForm {
    
   
    @NotNull
    private Integer paymentMethord;
    @NotNull
    private Integer addrId;
    
    private String paymentToken;
    
    private Integer couponId;
    private String notes;
    
    @NotNull
    Collection<OrderItemData> orderItem;
    

    public Integer getPaymentMethord() {
        return paymentMethord;
    }

    public void setPaymentMethord(Integer paymentMethord) {
        this.paymentMethord = paymentMethord;
    }

    public Collection<OrderItemData> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(Collection<OrderItemData> orderItem) {
        this.orderItem = orderItem;
    }

    public Integer getAddrId() {
        return addrId;
    }

    public void setAddrId(Integer addrId) {
        this.addrId = addrId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getPaymentToken() {
        return paymentToken;
    }

    public void setPaymentToken(String paymentToken) {
        this.paymentToken = paymentToken;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
