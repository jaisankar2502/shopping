/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.repository;

import com.shopping.entity.Features;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author ajmal
 */

public interface FeatureRepositry extends CrudRepository<Features,Integer>{
    Features save(Features features);
    Long countByStatus(Byte status);
    @Query(value = "SELECT *  FROM features where status = ?1 ", nativeQuery = true)
    Page<Features> findAllByStatus(Byte status,Pageable page);
    Optional<Features> findByFeaturesId(Integer feature_id);
    Optional<Features> findByFeaturesIdAndStatus(Integer feature_id,Byte status);

    public Collection<Features> findByStatusAndFeaturesIdIn(byte value, Collection<Integer> features);
}
