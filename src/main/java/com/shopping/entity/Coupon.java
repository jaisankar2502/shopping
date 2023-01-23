/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.entity;

import com.shopping.form.CouponForm;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
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
public class Coupon {

    public static enum Status {
        DELETED((byte) 0),
        ACTIVE((byte) 1),
        USED((byte) 2),
        EXPIRED((byte) 3),
        COUNTEXIDED((byte) 4);

        public final byte value;

        private Status(byte value) {
            this.value = value;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer couponId;
    private String description;
    private Integer amount;
    @Temporal(TemporalType.DATE)
    private Date expiry;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    private byte status;
    private Integer lim;
    private Integer count;
    private String tittle;
    private String couponCode;

    public Coupon() {
    }
    
    public Coupon(CouponForm form) throws ParseException {
        this.description = form.getDesc();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.expiry = sdf.parse(form.getExpiry()); 
        this.amount = form.getAmount();
        this.createdDate = new Date();
        this.updatedDate = new Date();
        this.status = Coupon.Status.ACTIVE.value;
        this.lim=form.getLimit();  
        this.tittle=form.getTittle();
        this.couponCode=form.getCouponCode();

    }
 public String createRandomCode(int codeLength){   
     char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < codeLength; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        System.out.println(output);
        return output ;
    }
    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public Integer getLim() {
        return lim;
    }

    public void setLim(Integer lim) {
        this.lim = lim;
    }
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    @Override
    public String toString() {
        return "Coupon{" + "couponId:" + couponId + ", description:" + description + ", amount=" + amount + ", expiry:" + expiry + ", createdDate:" + createdDate + ", updatedDate:" + updatedDate + ", status:" + status + ", lim:" + lim + ", count:" + count + ", tittle:" + tittle + ", couponCode:" + couponCode + '}';
    }    

}
