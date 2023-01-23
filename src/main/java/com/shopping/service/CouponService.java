/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.service;

import com.shopping.form.CouponForm;
import com.shopping.view.CouponView;
import java.util.List;

/**
 *
 * @author ajmal
 */
public interface CouponService {

    public void addCoupon(CouponForm form);

    public List<CouponView> getallCoupen(String status);

    public CouponView getCouponById(Integer couponId);

    public void deleteCouponById(Integer couponId);


    
}
