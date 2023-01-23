/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.service;

import com.shopping.form.UserAddressForm;
import com.shopping.view.UserAddressView;
import java.util.List;

/**
 *
 * @author ajmal
 */
public interface UsersAddressService {

    public UserAddressView addUserAddress(UserAddressForm form);

    public UserAddressView getUserAddressById(Integer addressId);

    public List<UserAddressView> getAllUserAddress(Integer limit);

    public void updateUserAddressById(Integer addressId,UserAddressForm form);

    public void deleteUserAddressById(Integer addressId);
    
}
