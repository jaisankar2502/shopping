/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.controller;

import com.shopping.form.ReviewForm;
import com.shopping.form.ReviewUpdateForm;
import com.shopping.service.ReviewService;
import com.shopping.util.Pager;
import com.shopping.view.ReviewDetailView;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ajmal
 */
@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public void addReview(@Valid @RequestBody ReviewForm form) {
        reviewService.addReview(form);

    }

    @GetMapping
    public Pager<ReviewDetailView> getAllReview(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "type", required = false) Boolean type
    ) {
        if (limit == null) {
            limit = 10;
        }
        if (page == null) {
            page = 1;
        }
        if (null == sort) {
            sort = "updatedDate";
        }
        if (null == type) {
            type = true;
        }

        return reviewService.getAllReview(limit, sort, type, page);

    }

    @GetMapping({"/{reviewId}"})
    public ReviewDetailView getReviewById(@PathVariable(name = "reviewId") Integer reviewId) {
        return reviewService.getReviewById(reviewId);
    }

    @PutMapping({"/{reviewId}"})
    public void updateReview(
            @PathVariable(name = "reviewId") Integer reviewId,
            @Valid @RequestBody ReviewUpdateForm form
    
    ) {
         reviewService.updateReview(reviewId,form);
    }
    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable(name = "reviewId") Integer reviewId){
        reviewService.deleteReview(reviewId);
    }

}
