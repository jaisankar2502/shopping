/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.service.impl;

import com.shopping.entity.Item;
import com.shopping.entity.Orderitem;
import com.shopping.entity.Orders;
import com.shopping.entity.Review;
import com.shopping.entity.User;
import com.shopping.exception.BadRequestException;
import com.shopping.exception.NotFoundException;
import com.shopping.form.ReviewForm;
import com.shopping.form.ReviewUpdateForm;
import com.shopping.repository.ItemRepository;
import com.shopping.repository.OrderItemRepository;
import com.shopping.repository.OrdersRepository;
import com.shopping.repository.ReviewRepository;
import com.shopping.security.util.SecurityUtil;
import com.shopping.service.ReviewService;
import com.shopping.util.LanguageUtil;
import com.shopping.util.Pager;
import com.shopping.view.ReviewDetailView;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author ajmal
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrdersRepository ordersRepository;
    
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private LanguageUtil languageUtil;

    @Override
    public void addReview(ReviewForm form) {

//        Orders order=ordersRepository.findByOrderIdAndUserUserId(form.getOrderId(),SecurityUtil.getCurrentUserId());
//        if(order==null){
//            throw new BadRequestException(languageUtil.getTranslatedText("invalid.order", null, "en"));
//        }
//        Orderitem orderitem=orderItemRepository.findByOrderIdAndItemId(order.getOrdersId(),form.getItemId());
//        if(orderitem==null){
//            throw new BadRequestException(languageUtil.getTranslatedText("invalid.order.item", null, "en"));
//        }
        Orderitem orderitem = orderItemRepository.getAllItemByUserIdAndItemId(form.getOrderId(), SecurityUtil.getCurrentUserId(), form.getItemId());

        if (orderitem == null) {
            throw new BadRequestException(languageUtil.getTranslatedText("invalid.order.item", null, "en"));
        }

        Review review = reviewRepository.findByItemItemIdAndUserUserIdAndOrdersOrdersId(form.getItemId(), SecurityUtil.getCurrentUserId(), form.getOrderId());
        if (review == null) {

            review = new Review();
            review.setItem(new Item(form.getItemId()));
            review.setOrders(new Orders(form.getOrderId()));
            review.setUser(new User(SecurityUtil.getCurrentUserId()));
            review.setReviews(form.getReview());
            review.setRate(form.getRate());
            review.setStatus(Review.Status.ACTIVE.value);
            review.setCreatedDate(new Date());
            review.setUpdatedDate(new Date());
        } else {
            review.setRate(form.getRate());
            review.setStatus(Review.Status.ACTIVE.value);
            review.setUpdatedDate(new Date());
        }
        review=reviewRepository.save(review);
        Float avgReview=reviewRepository.getAverageRate(review.getItem().getItemId(), Review.Status.ACTIVE.value);
        Item item=itemRepository.findByItemId(review.getItem().getItemId()).orElseThrow(()->new NotFoundException("item id not found"));
        item.setAvgrate(avgReview);
        item.setUpdateDate(new Date());
        itemRepository.save(item);
        
        

    }

    @Override
    public Pager<ReviewDetailView> getAllReview(Integer limit, String sort, Boolean type, Integer page) {

        Pager<ReviewDetailView> reviewPager;
        List<ReviewDetailView> reviewList;
        Long queryCount = null;
        queryCount = reviewRepository.ReviewCountByStatus(Review.Status.ACTIVE.value);
        reviewList = StreamSupport.stream(reviewRepository
                .findByStatus(Item.Status.ACTIVE.value,
                        PageRequest.of(page - 1, limit, (type == true) ? Sort.Direction.DESC : Sort.Direction.ASC, sort)).spliterator(), false)
                .map(rev -> new ReviewDetailView(rev))
                .collect(Collectors.toList());
        reviewPager = new Pager(limit, queryCount.intValue(), page);
        reviewPager.setResult(reviewList);
        return reviewPager;

    }

    @Override
    public ReviewDetailView getReviewById(Integer reviewId) {

        return new ReviewDetailView(reviewRepository.findByReviewIdAndStatus(reviewId, Review.Status.ACTIVE.value)
                .orElseThrow(() -> new NotFoundException(languageUtil.getTranslatedText("review.notfound", null, "en"))));

    }

    @Override
    public void updateReview(Integer reviewId, ReviewUpdateForm form) {
        Review review = reviewRepository.findByReviewIdAndUserUserIdAndStatus(reviewId, SecurityUtil.getCurrentUserId(), Review.Status.ACTIVE.value)
                .orElseThrow(() -> new NotFoundException(languageUtil.getTranslatedText("review.notfound", null, "en")));
        review.setReviews(form.getReview());
        review.setRate(form.getRate());
        review.setUpdatedDate(new Date());
        review=reviewRepository.save(review);
        Float avgReview=reviewRepository.getAverageRate(review.getItem().getItemId(), Review.Status.ACTIVE.value);
        Item item=itemRepository.findByItemId(review.getItem().getItemId()).orElseThrow(()->new NotFoundException("item id not found"));
        item.setAvgrate(avgReview);
        item.setUpdateDate(new Date());
        itemRepository.save(item);

    }

    @Override
    public void deleteReview(Integer reviewId) {
        Review review = reviewRepository.findByReviewIdAndUserUserIdAndStatus(reviewId, SecurityUtil.getCurrentUserId(), Review.Status.ACTIVE.value)
                .orElseThrow(() -> new NotFoundException(languageUtil.getTranslatedText("review.notfound", null, "en")));
        review.setStatus(Review.Status.DELETE.value);
        review.setUpdatedDate(new Date());
        review=reviewRepository.save(review);
        Float avgReview=reviewRepository.getAverageRate(review.getItem().getItemId(), Review.Status.ACTIVE.value);
        Item item=itemRepository.findByItemId(review.getItem().getItemId()).orElseThrow(()->new NotFoundException("item id not found"));
        item.setAvgrate(avgReview);
        item.setUpdateDate(new Date());
        itemRepository.save(item);        
        
    }

}
