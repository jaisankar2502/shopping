/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.repository;

import com.shopping.entity.Category;
import com.shopping.entity.SubCatLevel1;
import com.shopping.entity.SubCatLevel2;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

/**
 *
 * @author ajmal
 */
public interface SubCategoryLevel2Repository extends Repository<SubCatLevel2,Integer>{
    Optional<SubCatLevel2> findById(Integer id);

//    public List<SubCatLevel2> findAllByCategoryCategoryIdAndStatus(Integer CategoryId,byte value);

    public SubCatLevel2 save(SubCatLevel2 subCategory);
}
