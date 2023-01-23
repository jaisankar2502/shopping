/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.controller;

import com.shopping.service.TrackService;
import com.shopping.view.OrderTrackView;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ajmal
 */
@RestController
@RequestMapping("/order")
public class TrackController {
    @Autowired
    private TrackService trackService;
    
    @PutMapping("/{orderId}/accept")
    public void accept(@PathVariable("orderId") Integer orderId){
        trackService.Accept(orderId);
        
    }
    @PutMapping("/{orderId}/out")
    public void outForDelivery(@PathVariable("orderId") Integer orderId){
        trackService.outForDelivery(orderId);
        
    }
    @PutMapping("/{orderId}/delivered")
    public void delivered(@PathVariable("orderId") Integer orderId){
        trackService.delivered(orderId);          
    }
    @PutMapping("/{orderId}/reject")
    public void reject(@PathVariable("orderId") Integer orderId){
        trackService.reject(orderId);          
    }
    @PutMapping("/{orderId}/delete")
    public void delete(@PathVariable("orderId") Integer orderId){
        trackService.delete(orderId);          
    }
    
    @GetMapping("/track/{orderId}")
    public List<OrderTrackView> getTrack(@PathVariable("orderId") Integer orderId){
        return  trackService.getTrack(orderId);
    }
    
    
    
}
