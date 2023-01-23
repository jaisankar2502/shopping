/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.repository;

import com.shopping.entity.Information;
import com.shopping.form.InformationForm;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

/**
 *
 * @author ajmal
 */
public interface InformationRepository extends Repository<Information,Integer>{

    public Collection<Information> findByStatusAndInformationIdIn(byte value, Collection<Integer> information);

    public Information save(Information form);

    public List<Information> findAllByStatus(byte value);

    public Optional<Information> findByInformationIdAndStatus(Integer informationId,Byte status);

    public Optional<Information> findByInformationId(Integer informationId);

    
}
