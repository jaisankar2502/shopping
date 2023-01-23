/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.service.impl;

import com.shopping.entity.Item;
import com.shopping.entity.Review;
import com.shopping.entity.User;
import com.shopping.entity.Wishlist;
import com.shopping.exception.BadRequestException;
import com.shopping.form.WishlistForm;
import com.shopping.repository.ReviewRepository;
import com.shopping.repository.WishListRepository;
import com.shopping.security.util.SecurityUtil;
import com.shopping.service.WishListService;
import com.shopping.util.LanguageUtil;
import com.shopping.view.ReviewView;
import com.shopping.view.WishListView;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ajmal
 */
@Service
public class WishListServiceImpl implements WishListService {

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private LanguageUtil languageUtil;

    @Override
    public void addToWishlist(WishlistForm form) {
        Wishlist wishlist = wishListRepository.findByItemItemIdAndUserUserId(form.getItemId(), SecurityUtil.getCurrentUserId());
        if (wishlist != null) {
            if (form.getStatus() == 1) {
                wishlist.setStatus(Wishlist.Status.ACTIVE.value);
                wishlist.setUpdatedDate(new Date());
            }
            if (form.getStatus() == 0) {
                wishlist.setStatus(Wishlist.Status.DELETED.value);
                wishlist.setUpdatedDate(new Date());
            }
            wishListRepository.save(wishlist);

        } else {
            if (form.getStatus() == 1) {
                wishListRepository.save(
                        new Wishlist(
                                new User(SecurityUtil.getCurrentUserId()),
                                new Item(form.getItemId()),
                                new Date(),
                                new Date(),
                                Wishlist.Status.ACTIVE.value));
            }

        }

    }

    @Override
    public List<WishListView> getWishList() {
        return StreamSupport.stream(wishListRepository
                .findAllByUserUserIdAndStatus(SecurityUtil.getCurrentUserId(), Wishlist.Status.ACTIVE.value).spliterator(), false)
                .map((wish) -> {
                    List<ReviewView> review = StreamSupport.stream(reviewRepository
                            .findByItemIdAndStatus(wish.getItem().getItemId(), Review.Status.ACTIVE.value).spliterator(), false)
                            .map(rev -> new ReviewView(rev))
                            .collect(Collectors.toList());
                    Float avgRate = reviewRepository.getAverageRate(wish.getItem().getItemId(), Review.Status.ACTIVE.value);

                    return new WishListView(wish, review, avgRate);
                })
                .collect(Collectors.toList());
    }

    @Override
    public WishListView getById(Integer wishListId) {
        Wishlist wishList = wishListRepository.findBywishlistIdAndUserUserIdAndStatus(wishListId, SecurityUtil.getCurrentUserId(), Wishlist.Status.ACTIVE.value)
                .orElseThrow(() -> new BadRequestException(languageUtil.getTranslatedText("wishList.id.notfound", null, "en")));
        List<ReviewView> review = StreamSupport.stream(reviewRepository
                .findByItemIdAndStatus(wishList.getItem().getItemId(), Review.Status.ACTIVE.value).spliterator(), false)
                .map(rev -> new ReviewView(rev))
                .collect(Collectors.toList());
        Float avgRate = reviewRepository.getAverageRate(wishList.getItem().getItemId(), Review.Status.ACTIVE.value);
        return new WishListView(wishList, review, avgRate);

    }

    @Override
    public void updateById(Integer wishListId) {
        Wishlist wishList = wishListRepository.findBywishlistIdAndUserUserIdAndStatus(wishListId, SecurityUtil.getCurrentUserId(), Wishlist.Status.ACTIVE.value)
                .orElseThrow(() -> new BadRequestException(languageUtil.getTranslatedText("wishList.id.notfound", null, "en")));
        wishList.setStatus(Wishlist.Status.ACTIVE.value);
        wishList.setUpdatedDate(new Date());
        wishListRepository.save(wishList);
    }

    @Override
    public void deleteWishList(Integer wishListId) {
        Wishlist wishList = wishListRepository.findBywishlistIdAndUserUserIdAndStatus(wishListId, SecurityUtil.getCurrentUserId(), Wishlist.Status.ACTIVE.value)
                .orElseThrow(() -> new BadRequestException(languageUtil.getTranslatedText("wishList.id.notfound", null, "en")));
        wishListRepository.delete(wishList);

    }

}
