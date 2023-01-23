/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.controller;

import com.shopping.form.InformationForm;
import com.shopping.service.InformationService;
import com.shopping.view.InformationView;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ajmal
 */
@RestController
@RequestMapping("/infromation")
public class InformationController {
    @Autowired
    private InformationService informationService;
    
    @PostMapping 
    public void add(@RequestBody @Valid InformationForm form){
        informationService.add(form);        
    }
    
    @GetMapping
    public List<InformationView> getAll(){
        return informationService.getAll();
    }
    
    @GetMapping("/{informationId}")
    public InformationView getById(@PathVariable(name = "informationId") Integer informationId){
        return informationService.findById(informationId);
    }
    
    @PutMapping("/{informationId}")
    public void updateById(  
            @PathVariable(name = "informationId") Integer informationId,
            @RequestBody InformationForm form
    
    ){
         informationService.updateById(informationId,form);    
    }
    
    @DeleteMapping("/{informationId}")
    public void delete(@PathVariable(name = "informationId") Integer informationId){
        informationService.delete(informationId);
    }
    
    
}
