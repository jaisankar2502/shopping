/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.repository;

import com.shopping.entity.Orders;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

/**
 *
 * @author ajmal
 */
public interface OrdersRepository extends Repository<Orders, Integer> {

    public Orders save(Orders order);

    public List<Orders> findAllByUserUserIdOrderByCreatedDateDesc(Integer currentUserId);

    public Optional<Orders> findByOrdersId(Integer orders_id);

    public Optional<Orders> findByOrdersIdAndStatusNot(Integer orders_id, byte value);
    public Optional<Orders> findByOrdersIdAndStatus(Integer orders_id, byte value);


}
