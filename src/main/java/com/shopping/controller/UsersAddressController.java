/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.controller;

import com.shopping.form.UserAddressForm;
import com.shopping.service.UsersAddressService;
import com.shopping.view.UserAddressView;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ajmal
 */
@RestController
@RequestMapping("/users/address")
public class UsersAddressController {
    @Autowired
    private UsersAddressService usersAddressService;
    
    @PostMapping
    public UserAddressView addUserAddress(@RequestBody @Valid UserAddressForm form){
        return usersAddressService.addUserAddress(form);
    }
    
    @GetMapping("/{addressId}")
    public UserAddressView getUserAddressById(@PathVariable(name = "addressId") Integer addressId){
        return usersAddressService.getUserAddressById(addressId);    
    }
    
    @GetMapping
    public List<UserAddressView> getAllUserAddress(@RequestParam(name = "limit",required = false,defaultValue = "10") Integer limit){
        
        return usersAddressService.getAllUserAddress(limit);        
    }
    
    @PutMapping("/{addressId}")
    public void updateUserAddressById(
            @PathVariable(name = "addressId") Integer addressId,
            @RequestBody @Valid UserAddressForm form
    
    ){
        usersAddressService.updateUserAddressById(addressId,form); 
    }
    
    @DeleteMapping("/{addressId}")
    public void deleteUserAddressById(@PathVariable(name = "addressId") Integer addressId){
        usersAddressService.deleteUserAddressById(addressId);   

    }
    
    
}
