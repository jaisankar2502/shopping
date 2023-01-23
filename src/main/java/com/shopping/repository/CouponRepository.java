/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.repository;

import com.shopping.entity.Coupon;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ajmal
 */
public interface CouponRepository extends JpaRepository<Coupon, Integer>{

    public List<Coupon> findAllByStatusNotAndExpiryLessThan(byte value, Date date);

    public Optional<Coupon> findByCouponIdAndStatus(Integer couponId, byte value);

    @Override
    @Transactional
    public <S extends Coupon> S save(S entity);

    public List<Coupon> findAllByStatus(byte parseByte);
    
    public List<Coupon> findAllByStatusNotAndExpiryLessThanEqual(byte value, Date dt);
    
}
