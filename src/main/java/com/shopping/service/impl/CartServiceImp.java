/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.service.impl;

import com.shopping.entity.Cart;
import com.shopping.entity.Item;
import com.shopping.exception.NotFoundException;
import com.shopping.form.CartBulkForm;
import com.shopping.form.CartForm;
import com.shopping.form.CartItems;
import com.shopping.form.CartStatusChangeForm;
import com.shopping.repository.CartRepository;
import com.shopping.repository.ItemRepository;
import com.shopping.security.util.SecurityUtil;
import com.shopping.service.CartService;
import com.shopping.util.LanguageUtil;
import com.shopping.view.CartDetailView;
import com.shopping.view.CartView;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author albinps
 */
@Transactional
@Service
public class CartServiceImp implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private LanguageUtil languageUtil;

    @Override
    public CartView add(CartForm form) {
        Item item = itemRepository.findByItemIdAndStatus(form.getItemId(), Item.Status.ACTIVE.value)
                .orElseThrow(() -> new NotFoundException(languageUtil.getTranslatedText("item.notfound", null, "en")));

        Cart cart = cartRepository.findByUserUserIdAndItemAndStatusNot(SecurityUtil.getCurrentUserId(), item,Cart.Status.DELETED.value); 
        if (cart == null) {
            cart = new Cart(form, SecurityUtil.getCurrentUserId());
        } else {
            cart.setStatus(form.getIsCart() ? Cart.Status.CART.value : Cart.Status.SAVEFORLATER.value); 
            cart.setQty(cart.getQty() + form.getQty());
            cart.setUpdateDate(new Date());
        }
        cartRepository.save(cart);
        return new CartView(cart, item);
    }

    @Override
    public CartView get(Integer cartId) {
        Cart cart = cartRepository.findByCartId(cartId)
                .orElseThrow(()->new NotFoundException(languageUtil.getTranslatedText("cart.notfound", null, "en")));

        Item item = itemRepository.findByItemIdAndStatus(cart.getItem().getItemId(), Item.Status.ACTIVE.value)
                .orElseThrow(() -> new NotFoundException(languageUtil.getTranslatedText("item.notfound", null, "en")));

        return new CartView(cart, item);
    }

    @Override
    public CartDetailView list(String status) {
        List<CartView> cartView= StreamSupport.stream(cartRepository
                .findByUserUserIdAndStatus(SecurityUtil.getCurrentUserId(), Byte.parseByte(status)).spliterator(), false)
                .map(cart -> new CartView(cart))
                .collect(Collectors.toList());
        
        
        return new CartDetailView(cartView);
    }

    @Override
    public Cart update(Integer cartId) {
        Cart cart = cartRepository.findByCartId(cartId)
                .orElseThrow(()->new NotFoundException(languageUtil.getTranslatedText("cart.notfound", null, "en")));

        cart.setQty(cart.getQty() + 1);
        cart.setUpdateDate(new Date());
        cartRepository.save(cart);
        return null;  
    }

    @Override
    public Cart remove(Integer cartId) {
        Cart cart = cartRepository.findByCartId(cartId)
                .orElseThrow(()->new NotFoundException(languageUtil.getTranslatedText("cart.notfound", null, "en")));

        cart.setQty(cart.getQty() - 1);
        cart.setUpdateDate(new Date());
        cartRepository.save(cart);
        return null;
    }

    @Override
    public void addBulkCart(CartBulkForm form) {

        for (CartItems items : form.getCartForm()) {

            Item item = itemRepository.findByItemIdAndStatus(items.getItemId(), Item.Status.ACTIVE.value)
                    .orElseThrow(() -> new NotFoundException(languageUtil.getTranslatedText("item.notfound", null, "en")));

            Cart cart = cartRepository.findByUserUserIdAndItemAndStatus(SecurityUtil.getCurrentUserId(), item,Cart.Status.ACTIVE.value);
            if (cart == null) {
                cart = new Cart(items, SecurityUtil.getCurrentUserId(),form.getIsCart());  
            } else {
                cart.setStatus(form.getIsCart() ? Cart.Status.CART.value : Cart.Status.SAVEFORLATER.value);  
                cart.setQty(cart.getQty() + items.getQty());
                cart.setUpdateDate(new Date());
            }
            cartRepository.save(cart);

        }

    }

    @Override
    public List<CartView> listCheckoutCart() {
        return StreamSupport.stream(cartRepository
                .findCheckoutCartItem(SecurityUtil.getCurrentUserId(), Item.Status.ACTIVE.value, Cart.Status.CART.value).spliterator(), false)
                .map(cart -> new CartView(cart))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFromCart(Integer cartId) {
        Cart cart = cartRepository.findByCartId(cartId)
                .orElseThrow(()->new NotFoundException(languageUtil.getTranslatedText("cart.notfound", null, "en")));
        
            cartRepository.delete(cart);     

    }

    @Override
    public void changeCartStatus(CartStatusChangeForm form) { 
        
         Cart cart = cartRepository.findByCartId(form.getCartId())
                .orElseThrow(()->new NotFoundException(languageUtil.getTranslatedText("cart.notfound", null, "en")));
         
         if(form.getCartStatus()){
             cart.setStatus(Cart.Status.CART.value);
             
         }
         else{
             cart.setStatus(Cart.Status.SAVEFORLATER.value);
         }
         cartRepository.save(cart);
       
    }
}
