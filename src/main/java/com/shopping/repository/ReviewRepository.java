/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.repository;

import com.shopping.entity.Review;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ajmal
 */

@Repository
public interface ReviewRepository extends  JpaRepository<Review, Integer>{


    public Review findByItemItemIdAndUserUserIdAndOrdersOrdersId(Integer itemId, Integer currentUserId, Integer orderId);

    @Query(value = "select * from review where item_id=?1 and status=?2 order by created_date limit 10",nativeQuery = true)
    public List<Review> findByItemIdAndStatus(Integer itemId, byte value);

    @Query(value = "select AVG(rate) from review where item_id=?1 and status=?2",nativeQuery = true)
    public Float getAverageRate(Integer itemId, byte value);

    public List<Review> findByStatus(byte value);

    @Query(value = "select count(*) from review where status=?1",nativeQuery = true)
    public Long ReviewCountByStatus(byte value);

    public Page<Review> findByStatus(byte value, Pageable page);

    public Optional<Review> findByReviewIdAndStatus(Integer reviewId, byte value);

    public Optional<Review> findByReviewIdAndUserUserIdAndStatus(Integer reviewId, Integer currentUserId, byte value);

    public Optional<Review> findByReviewId(Integer reviewId);

    
        
}
