/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.repository;

import com.shopping.entity.Orderitem;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

/**
 *
 * @author ajmal
 */
public interface OrderItemRepository extends Repository<Orderitem, Integer> {

    public Orderitem save(Orderitem orderItem);

    @Query(value = "SELECT * FROM orderitem oi INNER JOIN orders o ON o.orders_id=oi.orders_id where o.user_id=?1", nativeQuery = true)
    public List<Orderitem> getAllItemByUserId(Integer userid);

    @Query(value = "SELECT * FROM orderitem oi INNER JOIN orders o ON o.orders_id=oi.orders_id where oi.orders_id=?1 and o.user_id=?2 and oi.item_id=?3", nativeQuery = true)
    public Orderitem getAllItemByUserIdAndItemId(Integer orderId, Integer userid, Integer itemId);

    public List<Orderitem> findAllByOrdersOrdersIdAndStatus(Integer orders_id, byte value);
//SELECT i.* FROM item i INNER JOIN orderitem oi ON i.item_id=oi.item_id where i.status= ?1 group by i.item_id order by count(i.item_id) desc

    @Query(value = "SELECT item_id FROM orderitem oi where oi.status=?1 group by oi.item_id order by count(oi.item_id) desc ", nativeQuery = true)
    public List<Integer> findAllBestOffersByStatus(byte value);

    @Query(value = "select * from orderitem oi where oi.orders_id=?1 order by orders_id desc ", nativeQuery = true)
    public List<Orderitem> findImage(Integer ordersId);

}
