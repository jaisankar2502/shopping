/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.service;

import com.shopping.form.PaymentTokenVerificationForm;
import com.shopping.form.testform;
import com.shopping.view.PaymentTokenView;

/**
 *
 * @author albinps
 */
public interface PaymentService {

    public String paymentToken(testform form);

    public PaymentTokenView setPaymentTime();

    public void paymentTokenVerify(PaymentTokenVerificationForm paymentTokenVerificationForm);
    
}
