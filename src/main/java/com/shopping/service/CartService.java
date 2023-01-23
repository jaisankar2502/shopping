/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.service;

import com.shopping.entity.Cart;
import com.shopping.form.CartBulkForm;
import com.shopping.form.CartForm;
import com.shopping.form.CartItemForm;
import com.shopping.form.CartStatusChangeForm;
import com.shopping.view.CartDetailView;
import com.shopping.view.CartView;
import java.util.List;

/**
 *
 * @author albinps
 */
public interface CartService {

    public CartView add(CartForm form);

    public CartView get(Integer cartId);

    public CartDetailView list(String status);

    public Cart update(Integer cartId);

    public Cart remove(Integer cartId);

    public void addBulkCart(CartBulkForm form);

    public List<CartView> listCheckoutCart();

    public void deleteFromCart(Integer cartId);

    public void changeCartStatus(CartStatusChangeForm form);

}
