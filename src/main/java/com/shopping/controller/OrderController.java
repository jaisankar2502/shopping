/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.controller;

import com.shopping.exception.BadRequestException;
import com.shopping.form.OrderForm;
import com.shopping.service.OrderService;
import com.shopping.util.LanguageUtil;
import com.shopping.util.Pager;
import com.shopping.view.ItemOrderDetailsView;
import com.shopping.view.OrderDetailView;
import com.shopping.view.OrderStatusView;
import com.shopping.view.OrderView;
import com.shopping.view.ResponseView;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ajmal
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private LanguageUtil languageUtil;
    
    
    @PostMapping
    public ResponseView addOrder(@RequestBody @Valid OrderForm form){
//        if(form.getPaymentId()!=null &&  form.getPaymentMethord()!=2){
//            return "Payment Successfully completed";
//            
//        }
        return orderService.addOrder(form);    
        
//        return "Cash on delivery success fully completed ";
    }
    
//    @GetMapping
//    public List<ItemOrderDetailsView> getAllOrder(){
//        return orderService.getAllOrder();
//    
//    }
    
   @GetMapping
    public Pager<ItemOrderDetailsView> getAllOrder(
            @RequestParam(value = "page", required = false, defaultValue = "1") String page,
            @RequestParam(value = "limit", required = false, defaultValue = "5") String limit,
            @RequestParam(value = "sort", required = false, defaultValue = "updatedDate") String sort,
            @RequestParam(value = "type", required = false, defaultValue = "false") boolean type,
            @RequestParam(value = "orderStatus", required = false) Byte[] orderStatus,
            @RequestParam(value = "orderTime", required = false) String[] orderTime,
            @RequestParam(value = "lastId", required = false) String lastId,
            @RequestParam(value = "search", required = false) String search){

        Integer lmt = null;

        Integer pge = null;

        try {
            lmt = Integer.parseInt(limit);
        } catch (NumberFormatException e) {
            throw new BadRequestException(languageUtil.getTranslatedText("invalid.limit.option", null, "en"));
        }

        try {
            pge = Integer.parseInt(page);
        } catch (NumberFormatException e) {
            throw new BadRequestException(languageUtil.getTranslatedText("invalid.page.option", null, "en"));
        }
   
        return orderService.getAllOrder( lmt, sort, type, pge,orderStatus,orderTime,lastId,search);
    }
    @GetMapping("/orders")
    public List<OrderView> getOrders(){
        return orderService.getOrders();
    
    }
    
    @GetMapping("/{orders_id}")
    public OrderDetailView getOrderById(@PathVariable(name = "orders_id") Integer orders_id){
        return orderService.getOrderById(orders_id);    
    }
    @DeleteMapping("/{orders_id}")
    public void cancelOrder(@PathVariable(name = "orders_id") Integer orders_id){
        orderService.cancelOrder(orders_id);
    }
    
    @PutMapping("return/{orders_id}")
    public void returnOrder(@PathVariable(name="orders_id") Integer orders_id){
        orderService.returnOrder(orders_id);
    }    
    
}
