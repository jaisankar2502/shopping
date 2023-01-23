/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.repository;

import com.shopping.entity.Payment;
import java.util.Optional;
import org.springframework.data.repository.Repository;

/**
 *
 * @author ajmal
 */
public interface PaymentRepository extends Repository<Payment,Integer>{

    public Payment save(Payment payment);

    public Optional<Payment> findByPaymentId(Integer a);
    
}
