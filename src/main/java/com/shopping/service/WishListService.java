/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.service;

import com.shopping.form.WishlistForm;
import com.shopping.view.WishListView;
import java.util.List;

/**
 *
 * @author ajmal
 */
public interface WishListService {

    public void addToWishlist(WishlistForm form);

    public List<WishListView> getWishList();

    public WishListView getById(Integer wishListId);

    public void updateById(Integer wishListId);

    public void deleteWishList(Integer wishListId);
    
}
