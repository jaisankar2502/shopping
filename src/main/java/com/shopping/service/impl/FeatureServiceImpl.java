/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.service.impl;

import com.shopping.entity.Features;
import com.shopping.exception.NotFoundException;
import com.shopping.form.FeatureForm;
import com.shopping.repository.FeatureRepositry;
import com.shopping.service.FeatureService;
import com.shopping.util.Pager;
import com.shopping.view.FeatureView;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author ajmal
 */
@Service
public class FeatureServiceImpl implements FeatureService{
    @Autowired
    private FeatureRepositry frepo;

    @Override
    public FeatureView add(FeatureForm form) {
        Features features=new Features();
        features.setFeatures_desc(form.getFeatures_desc());
        features.setStatus(Features.Status.ACTIVE.value);
        features.setCreatedDate(new Date());
        features.setUpdatedDate(new Date());
        features=frepo.save(features);
        return new FeatureView(features);
    }

    @Override
    public Pager<FeatureView> getAll(Integer limit, String sort, Boolean type, Integer page) {
        Pager<FeatureView> featurePage;
        Long quoryCount=null;
        List<FeatureView> featureList;
        quoryCount=frepo.countByStatus(Features.Status.ACTIVE.value);
        System.out.println("count------------>"+quoryCount);
        
         featureList = StreamSupport.stream(frepo
                .findAllByStatus(Features.Status.ACTIVE.value, 
                PageRequest.of(page - 1, limit, (type == true) ? Sort.Direction.DESC : Sort.Direction.ASC, sort)).spliterator(), false)
               .map(inquiry -> new FeatureView(inquiry))
                .collect(Collectors.toList());                
        featurePage = new Pager(limit,quoryCount.intValue(), page);
        featurePage.setResult(featureList);
        return featurePage; 
        
        
    }

    @Override
    public FeatureView getById(Integer featuresId) {
        Features feature=frepo.findByFeaturesId(featuresId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " not Found"));
        return new FeatureView(feature);
    }

    @Override
    public FeatureView updateById(Integer featuresId,FeatureForm form) {
       Features feature=frepo.findByFeaturesIdAndStatus(featuresId,Features.Status.ACTIVE.value).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, " not Found"));
       if(feature!=null){
           feature.setFeatures_desc(form.getFeatures_desc());
           feature.setUpdatedDate(new Date());
       }
       return new FeatureView(frepo.save(feature));
    }

    @Override
    public FeatureView delete(Integer featuresId) {
        Features feature=frepo.findByFeaturesIdAndStatus(featuresId,Features.Status.ACTIVE.value)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, " not Found"));
        feature.setStatus(Features.Status.DELETED.value);
        feature.setUpdatedDate(new Date());
        return new FeatureView(feature);
  
    }

    
    
}
