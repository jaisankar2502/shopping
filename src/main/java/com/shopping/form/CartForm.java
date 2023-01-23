/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.form;

import javax.validation.constraints.NotNull;

/**
 *
 * @author albinps
 */
public class CartForm {
    @NotNull
    private Integer qty;
    @NotNull
    private Integer itemId;
    
    private Boolean isCart;
    
    

    public Integer getQty() {
        return qty;
    }

    public Integer getItemId() {
        return itemId;
    }

    public Boolean getIsCart() {
        return isCart;
    }
    
    
    
}
