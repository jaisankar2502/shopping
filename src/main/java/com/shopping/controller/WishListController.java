/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.controller;

import com.shopping.form.WishlistForm;
import com.shopping.service.WishListService;
import com.shopping.view.WishListView;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ajmal
 */

@RestController
@RequestMapping("/wishlist")
public class WishListController {
    
    @Autowired
    private WishListService wishListService;
    
    @PostMapping
    public void addToWishlist(@Valid @RequestBody WishlistForm form){
           wishListService.addToWishlist(form);
    
    }
    
    @GetMapping
    public List<WishListView> getWishList(){
        return wishListService.getWishList();
    
    }
    
    @GetMapping("/{wishListId}")
    public WishListView getById(@PathVariable(value = "wishListId") Integer wishListId){
        return wishListService.getById(wishListId);
    
    }
    @PutMapping("/{wishListId}")
    public void updateById(@PathVariable(value = "wishListId") Integer wishListId){
         wishListService.updateById(wishListId);
    }
    @DeleteMapping("/{wishListId}")
    public void deleteWishList(@PathVariable(value = "wishListId") Integer wishListId){
        wishListService.deleteWishList(wishListId);
    }
}
