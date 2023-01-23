/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.view;

import com.shopping.entity.Orderitem;
import com.shopping.json.Json;
import java.util.Date;

/**
 *
 * @author ajmal
 */
public class ItemOrderDetailsView {
    
    private Integer itemOrderId;
    private float price;
    private Integer itemId;
    private String itemName;
    private String image1;
    private Integer paymentId;
    private byte payamentStatus;
    private Integer orderId;
    private byte orderStatus;
    private Integer shippingId;
    private byte shippingStatus; 
    private Float discount;
    private Integer qty;
    @Json.DateTimeFormat
    private Date createdDate;
    @Json.DateTimeFormat
    private Date updatedDate;
   

    public ItemOrderDetailsView(Orderitem orderItem){
        this.itemOrderId=orderItem.getOrderitemId();
        this.price=orderItem.getItem().getPrice();
        this.itemId=orderItem.getItem().getItemId();
        this.itemName=orderItem.getItem().getName();
        this.image1=orderItem.getItem().getImage1();
        this.paymentId=orderItem.getOrders().getPayment().getPaymentId();
        this.payamentStatus=orderItem.getOrders().getPayment().getStatus();
        this.orderId=orderItem.getOrders().getOrdersId();
        this.orderStatus=orderItem.getOrders().getStatus();
        this.shippingId=orderItem.getShipping().getShippingId();
        this.shippingStatus=orderItem.getShipping().getStatus();
        this.discount=(orderItem.getItem().getDiscount()!=null)?orderItem.getItem().getDiscount():0;
        this.createdDate=orderItem.getCreatedDate();
        this.updatedDate=orderItem.getUpdatedDate();
        this.qty=orderItem.getQty();
   }

    public Integer getItemOrderId() {
        return itemOrderId;
    }

    public void setItemOrderId(Integer itemOrderId) {
        this.itemOrderId = itemOrderId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public byte getPayamentStatus() {
        return payamentStatus;
    }

    public void setPayamentStatus(byte payamentStatus) {
        this.payamentStatus = payamentStatus;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getShippingId() {
        return shippingId;
    }

    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    public byte getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(byte shippingStatus) {
        this.shippingStatus = shippingStatus;
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

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
    
}
