/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.view;

import java.util.List;

/**
 *
 * @author ajmal
 */
public class CartDetailView {
    private Double totalPrice;
    private Double tax;
    private Double discount;
    private Double grandTotal;
    private List<CartView> cartView;
    
    public CartDetailView(List<CartView> cartView){
    
        this.cartView=cartView;
        this.totalPrice=this.cartView.stream().mapToDouble(price-> price.getPrice()*price.getQty()).sum();  
        this.discount= this.cartView.stream().mapToDouble(price->{
        if(price.getDiscount()!=null){
        return price.getDiscount()*price.getQty();
        }else{
         return 0;
        }
        
        } ).sum();
        this.tax=(this.totalPrice-this.discount)*18/100;
        this.grandTotal=this.totalPrice-this.discount+this.tax;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }
    
    public List<CartView> getCartView() {
        return cartView;
    }

    public void setCartView(List<CartView> cartView) {
        this.cartView = cartView;
    }
    
    
    
    
    
}
