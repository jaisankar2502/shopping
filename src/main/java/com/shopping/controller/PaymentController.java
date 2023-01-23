/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.controller;

import com.shopping.form.PaymentTokenVerificationForm;
import com.shopping.form.testform;
import com.shopping.service.PaymentService;
import com.shopping.view.PaymentTokenView;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author albinps
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-payment-intent")
    public String add(@Valid @RequestBody testform form) {
        return paymentService.paymentToken(form);

    }

    @GetMapping("/paymentexpiry")
    public PaymentTokenView setPaymentTime() {
        return paymentService.setPaymentTime();
    }

    @PostMapping("/paymentverify")
    public ResponseEntity paymentTokenVerify(@RequestBody PaymentTokenVerificationForm paymentTokenVerificationForm) {
        paymentService.paymentTokenVerify(paymentTokenVerificationForm);
        return new ResponseEntity(HttpStatus.OK);
    }

}
