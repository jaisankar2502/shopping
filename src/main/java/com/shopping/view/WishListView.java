/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.view;

import com.shopping.entity.Wishlist;
import com.shopping.json.Json;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ajmal
 */
public class WishListView {

    private Integer wishListId;
    private ItemView itemDetails;
    @Json.DateTimeFormat
    private Date createDate;
    @Json.DateTimeFormat
    private Date updateDate;

    public WishListView(Wishlist wishList, List<ReviewView> review,Float avgRate){
        
        this.wishListId=wishList.getWishlistId();
        this.itemDetails=new ItemView(wishList.getItem(),true,review,avgRate);
        this.createDate=wishList.getCreatedDate();
        this.updateDate=wishList.getUpdatedDate();    
    
    }
    
    public Integer getWishListId() {
        return wishListId;
    }

    public void setWishListId(Integer wishListId) {
        this.wishListId = wishListId;
    }

    public ItemView getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(ItemView itemDetails) {
        this.itemDetails = itemDetails;
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

}
