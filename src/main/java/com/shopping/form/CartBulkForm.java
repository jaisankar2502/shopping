/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.form;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author ajmal
 */
public class CartBulkForm {

    private Collection<CartItems> cartForm;
    
    private Boolean isCart;

    public Boolean getIsCart() {
        return isCart;
    }
    
    
    public Collection<CartItems> getCartForm() {
        return cartForm;
    }

    public void setCartForm(Collection<CartItems> cartForm) {
        this.cartForm = cartForm;
    }

}


