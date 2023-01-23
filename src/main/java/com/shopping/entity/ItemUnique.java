package com.shopping.entity;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author albinps
 */
@Entity
public class ItemUnique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemUniqueId;
    private String itemUniqueName;

    public ItemUnique() {
    }
     ItemUnique(Integer ItemUniqueId) {
       this.itemUniqueId=ItemUniqueId;
    }

    public Integer getItemUniqueId() {
        return itemUniqueId;
    }

    public void setItemUniqueId(Integer ItemUniqueId) {
        this.itemUniqueId = ItemUniqueId;
    }

    public String getItemUniqueName() {
        return itemUniqueName;
    }

    public void setItemUniqueName(String ItemUniqueName) {
        this.itemUniqueName = ItemUniqueName;
    }
    
}
