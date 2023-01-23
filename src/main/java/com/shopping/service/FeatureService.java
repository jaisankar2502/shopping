/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.service;

import com.shopping.form.FeatureForm;
import com.shopping.util.Pager;
import com.shopping.view.FeatureView;


/**
 *
 * @author ajmal
 */
public interface FeatureService {

    public FeatureView add(FeatureForm form);
    
    public Pager<FeatureView> getAll(Integer limit, String sort, Boolean type, Integer page);

    public FeatureView getById(Integer featuresId);

    public FeatureView updateById(Integer featuresId,FeatureForm form);

    public FeatureView delete(Integer featuresId);
    
}
