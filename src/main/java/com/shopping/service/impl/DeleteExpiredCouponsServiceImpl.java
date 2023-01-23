/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.service.impl;

import com.shopping.cron.DeleteExpiredCoupons;
import com.shopping.entity.Coupon;
import com.shopping.repository.CouponRepository;
import com.shopping.service.DeleteExpiredCouponsService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ajmal
 */
@Service
public class DeleteExpiredCouponsServiceImpl implements DeleteExpiredCouponsService {

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(DeleteExpiredCoupons.class);

    @Autowired
    private CouponRepository couponRepository;

    @Override
    public void deleteExpiredCoupons() {
        LOG.info("Batch exicuation Started  ");
        Date dt = java.sql.Date.valueOf(LocalDate.now());
        List<Coupon> coupons = couponRepository.findAllByStatusNotAndExpiryLessThan(Coupon.Status.EXPIRED.value, dt);
        LOG.info("Start the expired coupon status change with numbers of coupons " + coupons.size());
//        List<Coupon> coupons = StreamSupport.stream(couponRepository.findAllByStatusNotAndExpiryLessThanEqual(Coupon.Status.EXPIRED.value, dt)
//                .spliterator(), false)
//                .map((coupon) -> {
//                    LOG.info("Delete the coupen with id" + coupon.getCouponId());
//                    coupon.setStatus(Coupon.Status.EXPIRED.value);
//                    coupon.setUpdatedDate(new Date());
//                    try {
//                        couponRepository.save(coupon);
//                    } catch (Exception e) {
//                        LOG.info("Failed the " + coupon.getCouponId());
//                    }
//                    return coupon;
//                }).collect(Collectors.toList());
//        List<Coupon> failed= coupons.stream().filter(c->{
//        
//                    c.setStatus(Coupon.Status.EXPIRED.value);
//                    c.setUpdatedDate(new Date());
//                     try {
//                        couponRepository.save(c);
//                    } catch (Exception e) {
//                        LOG.info("Failed the " + c.getCouponId());
//                        return true;
//                    }
//                    return false;
//        
//        }).collect(Collectors.toList());

        List<Integer> success = new ArrayList<Integer>();
        List<Integer> failed = new ArrayList<Integer>();
        coupons.stream().forEach((coupon) -> {
            coupon.setStatus(Coupon.Status.EXPIRED.value);
            coupon.setUpdatedDate(new Date());
            try {
                couponRepository.save(coupon);
                LOG.info(coupon.getCouponId() + " Successfully deleted");
                success.add(coupon.getCouponId());
            } catch (Exception e) {
                LOG.info("Failed the " + coupon.getCouponId());
                failed.add(coupon.getCouponId());
            }

        });
        LOG.info("Deleted count " + success.size());
        LOG.info("Delete failed count " + failed.size());
        LOG.info("Batch exicuation completed successfully");
    }

}
