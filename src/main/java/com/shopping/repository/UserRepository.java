/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopping.repository;

import com.shopping.entity.Contact;
import com.shopping.entity.User;
import java.util.Collection;
import java.util.Optional;
import org.springframework.data.repository.Repository;

/**
 *
 * @author nirmal
 */
public interface UserRepository extends Repository<User, Integer> {

    Optional<User> findById(Integer userId);

    Optional<User> findByUserIdAndPassword(Integer userId, String password);
    
    

    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndType(String email,byte type);


    User save(User user);
    
    Collection<User> findAll();
    
    void delete(User user);
    
//    Optional<User> findByContactIdAndUserUserId(Integer contactId, Integer userId);
} 
