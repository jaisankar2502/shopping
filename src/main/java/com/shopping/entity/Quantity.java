/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author albinps
 */
@Entity
public class Quantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer quantity_id;
    private String quantityName;

    public Quantity() {
    }

    Quantity(Integer quantity_id) {
       this.quantity_id=quantity_id;
    }

    public Integer getQuantity_id() {
        return quantity_id;
    }

    public void setQuantity_id(Integer quantity_id) {
        this.quantity_id = quantity_id;
    }

    public String getQuantityName() {
        return quantityName;
    }

    public void setQuantityName(String quantityName) {
        this.quantityName = quantityName;
    }
    
}
