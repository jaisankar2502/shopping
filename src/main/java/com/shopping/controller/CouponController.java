/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.controller;

import com.shopping.form.CouponForm;
import com.shopping.service.CouponService;
import com.shopping.view.CouponView;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ajmal
 */
@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping
    public void addCoupon(@Valid @RequestBody CouponForm form) {
        form.setCouponCode(createRandomCode(5));
        couponService.addCoupon(form);
    }

    @GetMapping
    public List<CouponView> getallCoupen(@PathParam(value = "status") String status) {
        if (status == null) {
            status = "1";
        }
        return couponService.getallCoupen(status);

    }

    @GetMapping("/{couponId}")
    public CouponView getCouponById(@PathVariable(name = "couponId") Integer couponId) {
        return couponService.getCouponById(couponId);

    }

    @DeleteMapping("/{couponId}")
    public void deleteCouponById(@PathVariable(name = "couponId") Integer couponId) {
        couponService.deleteCouponById(couponId);
    }

    public String createRandomCode(int codeLength) {
        char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new SecureRandom();
        for (int i = 0; i < codeLength; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        System.out.println(output);
        return output;
    }

}
