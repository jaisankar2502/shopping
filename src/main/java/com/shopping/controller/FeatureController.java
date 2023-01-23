/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.controller;

import com.shopping.form.FeatureForm;
import com.shopping.service.FeatureService;
import com.shopping.util.Pager;
import com.shopping.view.FeatureView;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ajmal
 */
@RestController
@RequestMapping("/feature")
public class FeatureController {
    @Autowired
    private FeatureService fService;
        
    @PostMapping
    public FeatureView Add(@RequestBody FeatureForm form){
        return fService.add(form);
    }
    @GetMapping
    public Pager<FeatureView> getAll(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "type", required = false) Boolean type){
        if (limit == null) {
            limit = 10;
        }
        if (page == null) {
            page = 1;
        }
        if (null == sort) {
        sort = "features_id";
        }
        if (null == type) {
            type = true;
        }
        return fService.getAll(limit, sort, type, page);
    }
    @GetMapping("/{featuresId}")
    public FeatureView getById(@PathVariable(name = "featuresId") Integer featuresId){
        return fService.getById(featuresId);
    
    }
    @PutMapping("/{featuresId}")
    public FeatureView updateById(
            @PathVariable(name = "featuresId") Integer featuresId,
            @Valid @RequestBody FeatureForm form){
        return fService.updateById(featuresId,form);
    }
    
    @DeleteMapping("/{featuresId}")
    public FeatureView delete(@PathVariable(name = "featuresId") Integer featuresId){
        return fService.delete(featuresId);
    
    }
    
}
