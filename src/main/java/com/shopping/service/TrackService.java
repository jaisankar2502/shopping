/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.service;

import com.shopping.view.OrderTrackView;
import java.util.List;

/**
 *
 * @author ajmal
 */
public interface TrackService {

    public void Accept(Integer orderId);

    public void outForDelivery(Integer orderId);

    public void delivered(Integer orderId);

    public void reject(Integer orderId);

    public void delete(Integer orderId);

    public List<OrderTrackView> getTrack(Integer orderId);


    
}
