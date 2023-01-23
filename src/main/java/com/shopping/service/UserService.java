/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopping.service;

import com.shopping.entity.User;
import com.shopping.exception.BadRequestException;
import com.shopping.exception.NotFoundException;
import com.shopping.form.LoginForm;
import com.innovaturelabs.training.contacts.form.TokenRefreshForm;
import com.shopping.form.UserForm;
import com.shopping.form.GoogleLoginForm;
import com.shopping.form.PasswordChangeForm;
import com.shopping.form.ProfileUpdatesForm;
import com.shopping.util.Pager;
import com.shopping.view.LoginView;
import com.shopping.view.UserView;
import java.util.Collection;
import org.springframework.validation.Errors;

/**
 *
 * @author nirmal
 */
public interface UserService {

    UserView add(UserForm form);

    UserView currentUser();

    LoginView login(LoginForm form, Errors errors) throws BadRequestException;

    LoginView refresh(TokenRefreshForm form) throws BadRequestException;

    Collection<User> list();
    
    void delete(Integer userId) throws NotFoundException;

    UserView update(Integer userId, ProfileUpdatesForm form) throws NotFoundException;
    
    UserView get(Integer userId) throws NotFoundException;

    public LoginView signInWithGoogle(GoogleLoginForm form);

    public void changePassword(PasswordChangeForm form);

    public LoginView signInWithApple(GoogleLoginForm form);

    public Pager<UserView> SearchUser(Integer pg, Integer lmt, String sort, boolean type, String lastId, String search);
    
}
