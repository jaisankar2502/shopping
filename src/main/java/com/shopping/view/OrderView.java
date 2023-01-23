/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.view;

import com.shopping.entity.Orderitem;
import com.shopping.entity.Orders;
import com.shopping.json.Json;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ajmal
 */
public class OrderView {
    
    private Integer order_id;     
    private float amount;
    private byte currentStatus;
    @Json.DateTimeFormat
    private Date createdDate;
    @Json.DateTimeFormat
    private Date updatedDate;
    private CouponView coupon;
    private String notes;
    private String image;
    private Integer itemCount;
    
    public OrderView(Orders order, List<Orderitem> orderItem){
        this.order_id=order.getOrdersId();
        this.amount=order.getAmount();
        this.coupon=(order.getCoupon()==null)?null:new CouponView(order.getCoupon());
        this.currentStatus=order.getStatus();
        this.createdDate=order.getCreatedDate();
        this.updatedDate=order.getUpdatedDate();
        this.notes=order.getNotes();
        this.image=orderItem.get(0).getItem().getImage1();
        this.itemCount=orderItem.size();        
    
    }
    
    public OrderView() {
        
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public byte getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(byte currentStatus) {
        this.currentStatus = currentStatus;
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

    public CouponView getCoupon() {
        return coupon;
    }

    public void setCoupon(CouponView coupon) {
        this.coupon = coupon;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }
    
}
