/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.service;

import com.shopping.form.OrderForm;
import com.shopping.util.Pager;
import com.shopping.view.ItemOrderDetailsView;
import com.shopping.view.OrderDetailView;
import com.shopping.view.OrderStatusView;
import com.shopping.view.OrderView;
import com.shopping.view.ResponseView;
import java.util.List;

/**
 *
 * @author ajmal
 */
public interface OrderService {

    public ResponseView addOrder(OrderForm form);

//    public List<ItemOrderDetailsView> getAllOrder();

    public Pager<ItemOrderDetailsView> getAllOrder(Integer lmt, String sort, boolean type, Integer pge, Byte[] orderStatus, String[] orderTime, String lastId,String search);

    public List<OrderView> getOrders();

    public OrderDetailView getOrderById(Integer orders_id);

    public void cancelOrder(Integer orders_id);

    public void returnOrder(Integer orders_id);

    
}
