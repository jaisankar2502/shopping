/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.service.impl;

import com.google.gson.Gson;
import com.shopping.exception.BadRequestException;
import com.shopping.form.PaymentTokenVerificationForm;
import com.shopping.form.testform;
import com.shopping.security.config.SecurityConfig;
import com.shopping.security.util.InvalidTokenException;
import com.shopping.security.util.SecurityUtil;
import com.shopping.security.util.TokenExpiredException;
import com.shopping.security.util.TokenGenerator;
import com.shopping.security.util.TokenGenerator.Token;
import com.shopping.service.PaymentService;
import com.shopping.util.LanguageUtil;
import com.shopping.view.CreatePaymentResponse;
import com.shopping.view.PaymentTokenView;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author albinps
 */
@Service
public class PaymentServiceImpl  implements PaymentService{
    
     public static final String PURPOSE_PAYMENT_TOKEN = "PAYMENT_TOKEN";
     
    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private SecurityConfig securityConfig;
    
    @Autowired
    private LanguageUtil languageUtil; 
    

    public String paymentToken(testform form){
        Gson gson = new Gson();
        Stripe.apiKey = "sk_test_51L0ObwSIPnCRAs041Gl5dOCVNZXVysjDLWtWYMxzqB1mXSD8PXj2xwtjnMKUEoDVsQPw1EPZH7Tjlh4LjqgeNQm600EW0wfCjy";
        // CreatePayment postBody = gson.fromJson(form.toString(), testform.class);
        PaymentIntentCreateParams params =
        PaymentIntentCreateParams.builder()
          .setAmount(500000L)
          .setCurrency("inr")
    
          .build();
       PaymentIntent paymentIntent = null;
       try {
           paymentIntent = PaymentIntent.create(params);
       } catch (StripeException ex) {
           
       }

      CreatePaymentResponse paymentResponse = new CreatePaymentResponse(paymentIntent.getClientSecret());
      return gson.toJson(paymentResponse);
    }

    @Override
    public PaymentTokenView setPaymentTime() {
        
         String id = String.format("%010d", SecurityUtil.getCurrentUserId());
        
        Token paymentToken = tokenGenerator.create(PURPOSE_PAYMENT_TOKEN, id, securityConfig.getPaymentTokenExpiry());
        
        return new PaymentTokenView(paymentToken.value);   
    } 

    @Override
    public void paymentTokenVerify(PaymentTokenVerificationForm paymentTokenVerificationForm) {
        
        TokenGenerator.Status status; 
        try {
            status = tokenGenerator.verify(PURPOSE_PAYMENT_TOKEN, paymentTokenVerificationForm.getPaymentToken());
        } catch (InvalidTokenException e) {
            throw new BadRequestException(languageUtil.getTranslatedText("invalid.token", null, "en"));
        } catch (TokenExpiredException e) {
            throw new BadRequestException(languageUtil.getTranslatedText("payment.token.expired", null, "en")); 
        } 
    }  
    
     
    
}
