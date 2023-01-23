/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.repository;

import com.shopping.entity.Category;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

/**
 *
 * @author albinps
 */
public interface CategoryRepository extends  Repository<Category, Integer>{

     Optional<Category> findById(Integer categoryId);
    
     List<Category> findAllByStatus(byte status);

    public Category save(Category category);
    
}
