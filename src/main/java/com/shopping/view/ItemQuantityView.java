/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.view;

import com.shopping.entity.Item;

/**
 *
 * @author ajmal
 */
public class ItemQuantityView {
    
    private Integer ItemId;
    private String quantity;

    public ItemQuantityView(Item item) {
        this.ItemId = item.getItemId();
        this.quantity = item.getQuantity_id().getQuantityName();
    }

    public Integer getItemId() {
        return ItemId;
    }

    public void setItemId(Integer ItemId) {
        this.ItemId = ItemId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    
    
}
