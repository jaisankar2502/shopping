/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.service;

import com.shopping.form.InformationForm;
import com.shopping.form.InformationUpdateForm;
import com.shopping.view.InformationView;
import java.util.List;

/**
 *
 * @author ajmal
 */
public interface InformationService {

    public void add(InformationForm form);

    public List<InformationView> getAll();

    public InformationView findById(Integer informationId);


    public void updateById(Integer informationId, InformationForm form);

    public void delete(Integer informationId);

    
}
