/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.repository;

import com.shopping.entity.Category;
import com.shopping.entity.SubCatLevel1;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

/**
 *
 * @author ajmal
 */
public interface SubCategoryLevel1Repository extends Repository<SubCatLevel1,Integer>{
    Optional<SubCatLevel1> findById(Integer id);

    public List<SubCatLevel1> findAllByCategoryCategoryIdAndStatus(Integer CategoryId,byte value);

    public SubCatLevel1 save(SubCatLevel1 subCategory);

    public List<SubCatLevel1> findAllByStatusOrderByUpdatedDateDesc(byte value);
}
