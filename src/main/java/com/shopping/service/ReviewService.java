/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.service;

import com.shopping.form.ReviewForm;
import com.shopping.form.ReviewUpdateForm;
import com.shopping.util.Pager;
import com.shopping.view.ReviewDetailView;

/**
 *
 * @author ajmal
 */
public interface ReviewService {

    public void addReview(ReviewForm form);
    
    public Pager<ReviewDetailView> getAllReview(Integer limit, String sort, Boolean type, Integer page);

    public ReviewDetailView getReviewById(Integer reviewId);

    public void updateReview(Integer reviewId, ReviewUpdateForm form);

    public void deleteReview(Integer reviewId);

    
}
