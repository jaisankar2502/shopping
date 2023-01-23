/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.view;

import com.shopping.entity.Review;

/**
 *
 * @author ajmal
 */
public class ReviewView {

    private String reviewer;
    private String review;
    private Integer rate;
    
    public ReviewView(Review review){
        this.review=review.getReviews();
        this.rate=review.getRate();
        this.reviewer=review.getUser().getName();
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

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

}
