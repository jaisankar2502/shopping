/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.repository;

import com.shopping.entity.Wishlist;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ajmal
 */
@Repository
public interface WishListRepository extends JpaRepository<Wishlist, Integer>{

    public Wishlist findByItemItemIdAndUserUserId(Integer itemId, Integer currentUserId);

    public Wishlist findByItemItemIdAndUserUserIdAndStatus(Integer itemId, Integer currentUserId, byte value);

    public List<Wishlist> findAllByUserUserIdAndStatus(Integer currentUserId, byte value);


    public Optional<Wishlist> findBywishlistIdAndUserUserIdAndStatus(Integer wishListId, Integer currentUserId, byte value);
    
}
