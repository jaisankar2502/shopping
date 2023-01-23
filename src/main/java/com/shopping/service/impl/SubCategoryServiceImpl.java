/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.service.impl;

import com.shopping.entity.SubCatLevel1;
import com.shopping.entity.SubCatLevel2;
import com.shopping.form.SubCategoryLevel1Form;
import com.shopping.form.SubCategoryLevel2Form;
import com.shopping.service.SubCategoryService;
import com.shopping.view.SubCategoryLevel1View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shopping.repository.SubCategoryLevel1Repository;
import com.shopping.repository.SubCategoryLevel2Repository;
import com.shopping.view.SubCategoryLevel2View;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author albinps
 */
@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    private SubCategoryLevel1Repository subCategoryLevel1Repository;
    @Autowired
    private SubCategoryLevel2Repository subCategoryLevel2Repository;

   

    @Override
    public SubCategoryLevel1View addCategorylevel1(SubCategoryLevel1Form form) {
        return new SubCategoryLevel1View(subCategoryLevel1Repository.save(new SubCatLevel1(form)));
    }
    
    @Override
    public SubCategoryLevel2View addCategorylevel2(SubCategoryLevel2Form form) {
        return new SubCategoryLevel2View(subCategoryLevel2Repository.save(new SubCatLevel2(form)));
    }
    @Override
    public List<SubCategoryLevel1View> getSubcategoryList(){
        return subCategoryLevel1Repository.findAllByStatusOrderByUpdatedDateDesc(SubCatLevel1.Status.ACTIVE.value).stream().map(x->new SubCategoryLevel1View(x)).collect(Collectors.toList());
    }

}
