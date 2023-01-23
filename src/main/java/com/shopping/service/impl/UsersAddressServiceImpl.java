/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.service.impl;

import com.shopping.entity.Address;
import com.shopping.entity.User;
import com.shopping.exception.NotFoundException;
import com.shopping.form.UserAddressForm;
import com.shopping.repository.UserRepository;
import com.shopping.repository.UsersAddressRepository;
import com.shopping.security.util.SecurityUtil;
import com.shopping.service.UsersAddressService;
import com.shopping.view.UserAddressView;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ajmal
 */
@Service
public class UsersAddressServiceImpl implements UsersAddressService {

    @Autowired
    private UsersAddressRepository usersAddressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserAddressView addUserAddress(UserAddressForm form) {
        User user = userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(() -> new NotFoundException("User Not Found"));
        return new UserAddressView(usersAddressRepository.save(new Address(form, user)));
    }

    @Override
    public UserAddressView getUserAddressById(Integer addressId) {
        Address addr = usersAddressRepository.findByAddressIdAndUserUserIdAndStatus(addressId, SecurityUtil.getCurrentUserId(), Address.Status.ACTIVE.value).orElseThrow(() -> new NotFoundException("User Not Found"));
        return new UserAddressView(addr);
    }

    @Override
    public List<UserAddressView> getAllUserAddress(Integer limit) {
        List<UserAddressView> userAddressList = null;
        if (limit == 0) {
            userAddressList = usersAddressRepository.findAllByUserUserIdAndStatus(SecurityUtil.getCurrentUserId(), Address.Status.ACTIVE.value).stream().map(x -> new UserAddressView(x)).collect(Collectors.toList());
        } else {
            userAddressList = usersAddressRepository.findAllLimitAddresses(SecurityUtil.getCurrentUserId(), Address.Status.ACTIVE.value, limit).stream().map(x -> new UserAddressView(x)).collect(Collectors.toList());
        }
        return userAddressList;

    }

    @Override
    public void updateUserAddressById(Integer addressId, UserAddressForm form) {
        Address addr = usersAddressRepository.findByAddressIdAndUserUserIdAndStatus(addressId, SecurityUtil.getCurrentUserId(), Address.Status.ACTIVE.value).orElseThrow(() -> new NotFoundException("User Not Found"));
        addr.setName(form.getName());
        addr.setAddress(form.getAddress());
        addr.setCountry(form.getCountry());
        addr.setState(form.getState());
        addr.setCity(form.getCity());
        addr.setLandmark(form.getLandmark());
        addr.setZip(form.getZip());
        addr.setUpdatedDate(new Date());
        addr.setPhone(form.getPhone());
        switch (form.getType()) {
            case 1:
                addr.setType("home");
                break;
            case 2:
                addr.setType("work");
                break;
            default:
                addr.setType("others");
                break;
        }
        usersAddressRepository.save(addr);
    }

    @Override
    public void deleteUserAddressById(Integer addressId) {
        Address addr = usersAddressRepository.findByAddressIdAndUserUserId(addressId, SecurityUtil.getCurrentUserId())
                .orElseThrow(() -> new NotFoundException("User Not Found"));
        addr.setStatus(Address.Status.DELETED.value);
        addr.setUpdatedDate(new Date());
        usersAddressRepository.save(addr);
    }

}
