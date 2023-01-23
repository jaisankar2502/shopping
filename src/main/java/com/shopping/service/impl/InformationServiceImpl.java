/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.service.impl;

import com.shopping.entity.Information;
import com.shopping.form.InformationForm;
import com.shopping.form.InformationUpdateForm;
import com.shopping.service.InformationService;
import com.shopping.view.InformationView;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.shopping.repository.InformationRepository;

/**
 *
 * @author ajmal
 */
@Service
public class InformationServiceImpl implements InformationService{
    @Autowired
    private InformationRepository informationRepositry;

    @Override
    public void add(InformationForm form) {
        informationRepositry.save(new Information(form));
    }

    @Override
    public List<InformationView> getAll() {
        return informationRepositry.findAllByStatus(Information.Status.ACTIVE.value).stream().map(x->new InformationView(x)).collect(Collectors.toList());
    }

    @Override
    public InformationView findById(Integer informationId) {
        Information information= informationRepositry.findByInformationIdAndStatus(informationId,Information.Status.ACTIVE.value)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        return new InformationView(information);
    }   

    @Override
    public void updateById(Integer informationId,InformationForm form) {
        Information information= informationRepositry.findByInformationIdAndStatus(informationId,Information.Status.ACTIVE.value)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found")); 
        information.setBrand(form.getBrand());
        information.setCountry(form.getCountry());
        information.setManufacturer(form.getManufacturer());
        information.setUpdatedDate(new Date());
        informationRepositry.save(information);
    }

    @Override
    public void delete(Integer informationId) {
        Information information=informationRepositry.findByInformationId(informationId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        information.setStatus(Information.Status.DELETED.value);
        information.setUpdatedDate(new Date());
        informationRepositry.save(information);
    }
    
}
