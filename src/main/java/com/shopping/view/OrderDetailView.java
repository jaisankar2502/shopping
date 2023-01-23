/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.view;

import com.shopping.entity.Orderitem;
import com.shopping.entity.Orders;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *
 * @author ajmal
 */
public class OrderDetailView {

    private Integer orderId;
    private UserAddressView address;
    private CouponView coupon;
    private Double amount;
    private Integer couponAmount;
    private Double tax;
    private Double discount;
    private Double grandTotal;
    private String notes;
    private List<ItemOrderDetailsView> items;
    private byte currentStatus;
    private TrackView track;

    public OrderDetailView() {
    }

    public OrderDetailView(List<Orderitem> orderItem, Orders order, TrackView tracks) {
        this.orderId = order.getOrdersId();
        this.address = new UserAddressView(order.getAddr());
        this.coupon = (order.getCoupon() == null) ? null : new CouponView(order.getCoupon());
        this.notes = order.getNotes();
        this.items = StreamSupport.stream((orderItem).spliterator(), false)
                .map(item -> new ItemOrderDetailsView(item))
                .collect(Collectors.toList());
        this.discount = this.items.stream().mapToDouble(price -> {
            if (price.getDiscount() != null) {
                return price.getDiscount()*price.getQty();
            } else {
                return 0;
            }
        }).sum();
        this.amount = this.items.stream().mapToDouble(price -> {
            return (price.getPrice() * price.getQty());
        }).sum();

        Integer camount = (this.coupon == null || this.coupon.getAmount() == null) ? 0 : this.coupon.getAmount();
        this.couponAmount = camount;
        this.tax = (this.amount - this.discount - this.couponAmount) * 18 / 100;
        this.grandTotal = (double) this.amount - this.discount + this.tax - camount;

        this.currentStatus = order.getStatus();
        this.track = tracks;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public UserAddressView getAddress() {
        return address;
    }

    public void setAddress(UserAddressView address) {
        this.address = address;
    }

    public CouponView getCoupon() {
        return coupon;
    }

    public void setCoupon(CouponView coupon) {
        this.coupon = coupon;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<ItemOrderDetailsView> getItems() {
        return items;
    }

    public void setItems(List<ItemOrderDetailsView> items) {
        this.items = items;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public byte getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(byte currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Integer getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(Integer couponAmount) {
        this.couponAmount = couponAmount;
    }

    public TrackView getTrack() {
        return track;
    }

    public void setTrack(TrackView track) {
        this.track = track;
    }

}
