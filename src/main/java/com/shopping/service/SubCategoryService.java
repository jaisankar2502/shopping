/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.service;

import com.shopping.form.SubCategoryLevel1Form;
import com.shopping.form.SubCategoryLevel2Form;
import com.shopping.view.SubCategoryLevel1View;
import com.shopping.view.SubCategoryLevel2View;
import java.util.List;

/**
 *
 * @author albinps
 */
public interface SubCategoryService {

    public SubCategoryLevel1View addCategorylevel1(SubCategoryLevel1Form form);

    public SubCategoryLevel2View addCategorylevel2(SubCategoryLevel2Form form);

    public List<SubCategoryLevel1View> getSubcategoryList();

    
}
