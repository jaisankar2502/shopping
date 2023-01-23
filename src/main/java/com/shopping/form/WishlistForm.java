/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.form;

import javax.validation.constraints.NotNull;

/**
 *
 * @author ajmal
 */
public class WishlistForm {    
    
    @NotNull
    private Integer itemId;
    
    @NotNull
    private byte status;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }    
                
}
