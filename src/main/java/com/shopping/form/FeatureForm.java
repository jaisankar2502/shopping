/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author ajmal
 */
public class FeatureForm {
   
    @NotBlank
    @Size(max=200)
    private String features_desc;
  
    public String getFeatures_desc() {
        return features_desc;
    }

    public void setFeatures_desc(String features_desc) {
        this.features_desc = features_desc;
    }   

    
}
