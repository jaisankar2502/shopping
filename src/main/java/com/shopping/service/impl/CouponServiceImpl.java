/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.service.impl;

import com.shopping.entity.Coupon;
import com.shopping.exception.BadRequestException;
import com.shopping.exception.NotFoundException;
import com.shopping.form.CouponForm;
import com.shopping.repository.CouponRepository;
import com.shopping.service.CouponService;
import com.shopping.util.LanguageUtil;
import com.shopping.view.CouponView;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ajmal
 */
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private LanguageUtil languageUtil;

    @Override
    public void addCoupon(CouponForm form) {
        try {
            couponRepository.save(new Coupon(form));
        } catch (ParseException ex) {
            Logger.getLogger(CouponServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<CouponView> getallCoupen(String status) {
        return StreamSupport.stream(couponRepository.
                findAllByStatus(Byte.parseByte(status)).spliterator(), false)
                .map(coupon -> new CouponView(coupon))
                .collect(Collectors.toList());

    }

    @Override
    public CouponView getCouponById(Integer couponId) {
        return new CouponView(couponRepository.findById(couponId)
                .orElseThrow(() -> new NotFoundException(languageUtil.getTranslatedText("coupon.notfound", null, "en"))));

    }

    @Override
    public void deleteCouponById(Integer couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new NotFoundException(languageUtil.getTranslatedText("coupon.notfound", null, "en")));
        Date dt1 = coupon.getExpiry();
        Date dt2 = java.sql.Date.valueOf(LocalDate.now());
        if (dt1.before(dt2)) {
            throw new BadRequestException(languageUtil.getTranslatedText("coupon.expired", null, "en"));
        }
        if (coupon.getStatus() == Coupon.Status.DELETED.value) {
            throw new BadRequestException(languageUtil.getTranslatedText("coupon.already.deleted", null, "en"));

        }
        coupon.setStatus(Coupon.Status.DELETED.value);
        coupon.setUpdatedDate(new Date());
        couponRepository.save(coupon);

    }

}
