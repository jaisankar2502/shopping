/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.entity;

import com.shopping.form.OrderForm;
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
public class Payment {

 
    public static enum PaymentType{
        EMPTY((byte) 0),
        CARD((byte) 1),
        CASH((byte) 2),
        UPI((byte) 3),
        BANK((byte) 4);
        public final byte value;        

        private PaymentType(byte value){
            this.value=value;
        }
    
    }
    
    public static enum Status {
        DELETED((byte) 0),
        INPROGRESS((byte) 1),
        CASHONDEVLIVARY((byte) 2),
        SUCCESS((byte) 3),
        FAILED((byte) 4);

        public final byte value;

        private Status(byte value) {
            this.value = value;
        }
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;
    private byte paymentType;
    private byte status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    
    
    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public byte getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(byte paymentType) {
        this.paymentType = paymentType;
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
