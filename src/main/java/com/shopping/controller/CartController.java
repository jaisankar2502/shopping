/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.controller;

import com.shopping.entity.Cart;
import com.shopping.form.CartBulkForm;
import com.shopping.form.CartForm;
import com.shopping.form.CartItemForm;
import com.shopping.form.CartStatusChangeForm;
import com.shopping.service.CartService;
import com.shopping.view.CartDetailView;
import com.shopping.view.CartView;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author albinps
 */
@RestController
@RequestMapping("/cart")
public class CartController {
    
     @Autowired
    private CartService cartService;
    @PostMapping
    public CartView add(@Valid @RequestBody CartForm form) {
        return cartService.add(form);
    }
    
    @PostMapping("/add")
    public void addBulkCart(@RequestBody CartBulkForm form){
        cartService.addBulkCart(form);
    }
    
    @GetMapping
    public  CartDetailView list(@RequestParam(value = "status", required = true) String status) {
        return cartService.list(status);
    }
    @GetMapping("/checkout")
    public  List<CartView> listCheckoutCart() {
        return cartService.listCheckoutCart();
    }
    @GetMapping("/{cartId}")
    public CartView get(@PathVariable("cartId") Integer cartId) {
        return cartService.get(cartId);
    }
    @PutMapping("/update/{cartId}")
    public Cart update(@PathVariable("cartId") Integer cartId) {
        return cartService.update(cartId);
    }
     @PutMapping("/remove/{cartId}")
    public Cart remove(@PathVariable("cartId") Integer cartId) {
        return cartService.remove(cartId);
    }
    @DeleteMapping("/{cartId}")
    public void deletefromCart(@PathVariable("cartId") Integer cartId) {
         cartService.deleteFromCart(cartId);
    }
    @PostMapping("/changeStatus")
    public void changeCartStatus(@RequestBody CartStatusChangeForm form) {
         cartService.changeCartStatus(form);
    }
}
