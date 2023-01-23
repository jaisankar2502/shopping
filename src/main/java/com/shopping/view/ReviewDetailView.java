/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.view;

import com.shopping.entity.Review;
import com.shopping.json.Json;
import java.util.Date;

/**
 *
 * @author ajmal
 */
public class ReviewDetailView {

    private Integer reviewId;
    private String reviewer;
    private String review;
    private Integer rate;
    private ItemView item;
    @Json.DateTimeFormat
    private Date createDate;
    @Json.DateTimeFormat
    private Date updateDate;

    public ReviewDetailView(Review reviews){
        this.reviewId=reviews.getReviewId();
        this.reviewer=reviews.getUser().getName();
        this.review=reviews.getReviews();
        this.item=new ItemView(reviews.getItem());
        this.rate=reviews.getRate();
        this.createDate=reviews.getCreatedDate();
        this.updateDate=reviews.getUpdatedDate();
    
    }
    
    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public ItemView getItem() {
        return item;
    }

    public void setItem(ItemView item) {
        this.item = item;
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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

}
