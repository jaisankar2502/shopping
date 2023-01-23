/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.security.util;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.shopping.exception.BadRequestException;
import com.shopping.form.GoogleLoginForm;
import com.shopping.util.LanguageUtil;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author ajmal
 */
@Component
public class SignInWithGoogleUtils {

    @Value("${google.clientId}")
    private String clientId;
    
    @Value("${google.clientId.web}")
    private String clientIdWeb;
    
    @Autowired
    private LanguageUtil languageUtil;
    
    Logger log=LoggerFactory.getLogger(SignInWithGoogleUtils.class);
    public Payload SigninwithGoogle(GoogleLoginForm form,Integer type) throws GeneralSecurityException, IOException {
        NetHttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        Payload payload = null;
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList((type==1)?clientIdWeb:clientId))
                .build();

        System.out.println("clintId========>" + clientId);
        System.out.println("verifier=============>" + verifier);
        System.out.println("form Id token==========>" + form.getIdToken());
        GoogleIdToken idToken = verifier.verify(form.getIdToken());
        
        System.out.println("id token==================>" + idToken);

        if (idToken != null) {
            payload = idToken.getPayload();
        }else{
            log.error("Invalid ID token.");
            throw new BadRequestException(languageUtil.getTranslatedText("invalid.id.token", null, "en"));
        }
        return payload;
    }
    
}
