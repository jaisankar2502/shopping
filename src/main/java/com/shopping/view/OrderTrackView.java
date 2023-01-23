/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.view;

import com.shopping.entity.Track;
import java.util.Date;
import org.aspectj.weaver.tools.Trace;

/**
 *
 * @author ajmal
 */
public class OrderTrackView {
    
    private Integer orderId;
    private Integer trackId;
    private byte status;
    private Date createdDate;
    private Date updatedDate;
    private String reason;

    public OrderTrackView() {
    }

    public OrderTrackView(Track track) {
        this.orderId = track.getOrders().getOrdersId();
        this.trackId = track.getTrackId();
        this.status = track.getStatus();
        this.createdDate = track.getCreatedDate();
        this.updatedDate = track.getUpdatedDate();
        this.reason = track.getReason();
        
    }

    
    
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
           
     
    
    
}
