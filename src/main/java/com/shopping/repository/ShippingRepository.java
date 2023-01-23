/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.repository;

import com.shopping.entity.Shipping;
import java.util.Optional;
import org.springframework.data.repository.Repository;

/**
 *
 * @author ajmal
 */
public interface ShippingRepository extends Repository<Shipping, Integer>{

    public Optional<Shipping> findByShippingId(Integer shippingId);

    public Shipping save(Shipping shipping);
    
}
