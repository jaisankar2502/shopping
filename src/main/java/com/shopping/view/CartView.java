/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.view;

import com.shopping.entity.Cart;
import com.shopping.entity.Item;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author albinps
 */
public class CartView {

    private final Integer cartId;
    private final Integer qty;
    private final byte cartstatus;
    private final byte itemstatus;
    private final Integer itemId;
    private final String name;
    private final Float MRP;
    private final Float price;
    private final String image1;
    private final String image2;
    private final String image3;
    private final String description;
    @Temporal(TemporalType.TIMESTAMP)
    private final Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private final Date updateDate;
    private final Integer category;
    private final Integer stock;
    private final Float discount;

    public CartView(Cart cart, Item item) {
        this.cartId = cart.getCartId();
        this.qty = cart.getQty();
        this.itemId = item.getItemId();
        this.name = item.getName();
        this.MRP = item.getMrp();
        this.price = item.getPrice();
        this.image1 = item.getImage1();
        this.image2 = item.getImage2();
        this.image3 = item.getImage3();
        this.itemstatus = item.getStatus();
        this.cartstatus = cart.getStatus();
        this.description = item.getDescription();
        this.createDate = cart.getCreateDate();
        this.updateDate = cart.getUpdateDate();
        this.category = item.getCategory().getCategoryId();
        this.stock = item.getStock();
        this.discount = item.getDiscount();
        
    }

    public CartView(Cart cart) {
        this.cartId = cart.getCartId();
        this.qty = cart.getQty();
        this.itemId = cart.getItem().getItemId();
        this.name = cart.getItem().getName();
        this.MRP = cart.getItem().getMrp();
        this.price = cart.getItem().getPrice();
        this.image1 = cart.getItem().getImage1();
        this.image2 = cart.getItem().getImage2();
        this.image3 = cart.getItem().getImage3();
        this.cartstatus = cart.getStatus();
        this.itemstatus = cart.getItem().getStatus();
        this.description = cart.getItem().getDescription();
        this.createDate = cart.getCreateDate();
        this.updateDate = cart.getUpdateDate();
        this.category = cart.getItem().getCategory().getCategoryId();
        this.stock = cart.getItem().getStock();
        this.discount =  cart.getItem().getDiscount();
    }

    public Integer getCartId() {
        return cartId;
    }

    public Integer getQty() {
        return qty;
    }

    public byte getCartstatus() {
        return cartstatus;
    }

    public byte getItemstatus() {
        return itemstatus;
    }

    public Integer getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public Float getMRP() {
        return MRP;
    }

    public Float getPrice() {
        return price;
    }

    public String getImage1() {
        return image1;
    }

    public String getImage2() {
        return image2;
    }

    public String getImage3() {
        return image3;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public Integer getCategory() {
        return category;
    }

    public Integer getStock() {
        return stock;
    }

    public Float getDiscount() {
        return discount;
    }

//    public void setDiscount(Float discount) {
//        this.discount = discount;
//    }
}
