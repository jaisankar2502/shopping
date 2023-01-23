/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.repository;

import com.shopping.entity.Address;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

/**
 *
 * @author ajmal
 */
public interface UsersAddressRepository extends Repository<Address,Integer>{

    public Address save(Address address);

    public Optional<Address> findByAddressIdAndUserUserId(Integer addressId, Integer currentUserId);

    public List<Address> findAllByUserUserIdAndStatus(Integer currentUserId, byte status);    
    
    @Query(value = "SELECT * FROM address where user_id = ?1 and status = ?2 limit ?3 ", nativeQuery = true)
    public List<Address> findAllLimitAddresses(Integer currentUserId, byte value,Integer limit);
    
    public Optional<Address> findByAddressIdAndUserUserIdAndStatus(Integer addressId, Integer currentUserId,byte status);
}
