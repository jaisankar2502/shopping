/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.util;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

/**
 *
 * @author ajmal
 */
@Component
public class AppleLoginUtil {

    @Autowired
    private LanguageUtil languageUtil;
    Logger log = LoggerFactory.getLogger(AppleLoginUtil.class);

    private final static String APPLE_AUTH_URL = "https://appleid.apple.com/auth/token";

    private final static String KEY_ID = "7DGF76ZQ59";
    private final static String TEAM_ID = "2633M77RVP";
    private final static String CLIENT_ID = "com.innovature.kanbutsuya";
    // private static String WEB_CLIENT_ID = "com.your.bundle.id.web";

    private static PrivateKey pKey;

    private static PrivateKey getPrivateKey() throws Exception {
//        //read your key
//        String path = new ClassPathResource("AuthKey_Y6375P7786.p8").getFile().getAbsolutePath();
//
//        final PEMParser pemParser = new PEMParser(new FileReader(path));
//        final JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
//        final PrivateKeyInfo object = (PrivateKeyInfo) pemParser.readObject();
//        final PrivateKey pKey = converter.getPrivateKey(object);
////        return pKey;
//        File file = ResourceUtils.getFile("classpath:AuthKey_Y6375P7786.p8");
//        final PEMParser pemParser = new PEMParser(new FileReader(file));
//        final JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
//        final PrivateKeyInfo object = (PrivateKeyInfo) pemParser.readObject();
//        final PrivateKey pKey = converter.getPrivateKey(object);
//        pemParser.close();

        ClassPathResource cpr = new ClassPathResource("AuthKey_Y6375P7786.p8");

        byte[] bdata = FileCopyUtils.copyToByteArray(cpr.getInputStream());
        PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(new org.apache.commons.codec.binary.Base64().decode(bdata));
        PrivateKey appleKey = KeyFactory.getInstance("EC").generatePrivate(priPKCS8);
        return appleKey;
    }

    private static String generateJWT() throws Exception {
        if (pKey == null) {
            pKey = getPrivateKey();
        }

        String token = Jwts.builder()
                .setHeaderParam(JwsHeader.KEY_ID, KEY_ID)
                .setIssuer(TEAM_ID)
                .setAudience("https://appleid.apple.com")
                .setSubject(CLIENT_ID)
                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 5)))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(pKey, SignatureAlgorithm.ES256)
                .compact();

        return token;
    }

    /*
    * Returns unique user id from apple
    * */
    public IdTokenPayload appleAuth(String authorizationCode, boolean forWeb) throws Exception {

        HttpResponse<String> response = Unirest.post(APPLE_AUTH_URL)
                .header("Content-Type", "application/x-www-form-urlencoded")
                //                .field("client_id", forWeb ? WEB_CLIENT_ID : CLIENT_ID)
                //                .field("client_secret", forWeb ? generateWebJWT() : generateJWT())
                .field("client_id", CLIENT_ID)
                .field("client_secret", generateJWT())
                .field("grant_type", "authorization_code")
                .field("code", authorizationCode)
                .asString();
        System.out.println("========>" + response.getBody());

        TokenResponse tokenResponse = new Gson().fromJson(response.getBody(), TokenResponse.class);
        if (tokenResponse.getId_token() == null) {

            throw new NullPointerException(languageUtil.getTranslatedText("invalid.id.token", null, "en"));
        }
        System.out.println("tokenResponse" + tokenResponse.getId_token());
        String idToken = tokenResponse.getId_token();
        String payload = idToken.split("\\.")[1];//0 is header we ignore it for now
        String decoded = new String(Decoders.BASE64.decode(payload));

        IdTokenPayload idTokenPayload = new Gson().fromJson(decoded, IdTokenPayload.class);

        return idTokenPayload;

    }

}
