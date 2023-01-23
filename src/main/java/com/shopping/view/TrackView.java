/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.view;

import com.shopping.json.Json;
import java.util.Date;

/**
 *
 * @author ajmal
 */
public class TrackView {

    @Json.DateTimeFormat
    private Date pendingDate;
    @Json.DateTimeFormat
    private Date acceptedDate;
    @Json.DateTimeFormat
    private Date outDate;
    @Json.DateTimeFormat
    private Date deliveredDate;
    @Json.DateTimeFormat
    private Date rejectedDate;
    @Json.DateTimeFormat
    private Date cancelledDate;
    @Json.DateTimeFormat
    private Date paymentFailedDate;
    @Json.DateTimeFormat
    private Date returnDate;

    public Date getPendingDate() {
        return pendingDate;
    }

    public void setPendingDate(Date pendingDate) {
        this.pendingDate = pendingDate;
    }

    public Date getAcceptedDate() {
        return acceptedDate;
    }

    public void setAcceptedDate(Date acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public Date getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(Date deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public Date getRejectedDate() {
        return rejectedDate;
    }

    public void setRejectedDate(Date rejectedDate) {
        this.rejectedDate = rejectedDate;
    }

    public Date getCancelledDate() {
        return cancelledDate;
    }

    public void setCancelledDate(Date cancelledDate) {
        this.cancelledDate = cancelledDate;
    }   

    public Date getPaymentFailedDate() {
        return paymentFailedDate;
    }

    public void setPaymentFailedDate(Date paymentFailedDate) {
        this.paymentFailedDate = paymentFailedDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
