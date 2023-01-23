/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopping.controller;

import com.shopping.form.LoginForm;
import com.innovaturelabs.training.contacts.form.TokenRefreshForm;
import com.shopping.form.GoogleLoginForm;
import com.shopping.service.UserService;
import com.shopping.view.LoginView;
import java.io.IOException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nirmal
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

   

    @PostMapping
    public LoginView login(@Valid @RequestBody LoginForm form, Errors errors) {
        return userService.login(form, errors);
    }

    @PutMapping
    public LoginView refresh(@RequestBody TokenRefreshForm form) {
        return userService.refresh(form);
    }
    @PostMapping("/google")
    public LoginView signInWithGoogle(@Valid @RequestBody GoogleLoginForm form, Errors errors){
        return userService.signInWithGoogle(form);
    }
    
    @PostMapping("/apple")
    public LoginView signInWithApple(@Valid @RequestBody GoogleLoginForm form, Errors errors) throws IOException{
        return userService.signInWithApple(form);
    }
    
    
    
}
