/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.repository;

import com.shopping.entity.Cart;
import com.shopping.entity.Item;
import com.shopping.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

/**
 *
 * @author albinps
 */
public interface CartRepository  extends Repository<Cart, Integer> {
    Cart save(Cart cart);
    
    Optional<Cart> findByCartId(Integer cartId);
    
    void delete(Cart cart);
    
    Cart findByUserUserIdAndItemAndStatus(Integer currentUserId, Item itemId,byte status);

    Cart findByUserUserIdAndItem(Integer currentUserId, Item itemId); 
    
    Cart findByUserUserIdAndItemAndStatusNot(Integer currentUserId, Item itemId,byte status);

    @Query("SELECT c from Cart c join fetch c.item where  c.user.userId = ?1 and c.status = ?2")
    List<Cart> findByUserUserIdAndStatus(Integer currentUserId,Byte status);
    
    @Query(value = "select * from cart c join item i on i.item_id=c.item_id where c.user_id= ?1 and  c.status=?3 and i.status= ?2 and i.stock >=c.qty ",nativeQuery = true)
    List<Cart> findCheckoutCartItem(Integer currentUserId,Byte itemstatus,Byte carttatus);
    @Query(value = "select * from cart where user_id=?1 and item_id IN(?2)",nativeQuery = true)
    public List<Cart> findByUserUserIdAndItemIdIn(Integer userId, List<Integer> itemId);
    
     

}
