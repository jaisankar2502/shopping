/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.repository;

import com.shopping.entity.Item;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author albinps
 */
public interface ItemRepository extends CrudRepository<Item, Integer> {

    Item save(Item item);

    List<Item> findAllByCategoryCategoryId(Integer categoryId);

    Optional<Item> findByItemId(Integer itemId);

    Optional<Item> findByItemIdAndStatus(Integer itemId, Byte status);

    @Query(value = "SELECT count(*)  FROM item where status = ?1 and name like %?2% and sub_cat_level1_id=?3", nativeQuery = true)
    Long countItemList(byte value, String search, Integer subcatId);

    @Query(value = "SELECT *  FROM item where status = ?1 and name like %?2%  and sub_cat_level1_id=?3", nativeQuery = true)
    Page<Item> getItemList(byte value, String search, Integer subcatId, Pageable page);

    List<Item> findByItemIdIn(Collection<Integer> item);

    @Query(value = "select * from item  where status = ?1 ORDER by (((mrp-price-discount)/mrp)*100) desc LIMIT 10", nativeQuery = true)
    List<Item> findBestoffers(byte value);

    @Query(value = "select * from item  where status = ?1 ORDER by (((mrp-price-discount)/mrp)*100) desc", nativeQuery = true)
    Page<Item> findAllBestoffers(byte value, Pageable page);

    @Query(value = "select count(*) from item  where status = ?1 ORDER by (((mrp-price-discount)/mrp)*100)", nativeQuery = true)
    Long countAllBestoffers(byte value);

    @Query(value = "SELECT *  FROM item where status = ?1 ORDER by create_date desc LIMIT 10", nativeQuery = true)
    List<Item> findnewProduct(byte value);

    @Query(value = "SELECT *  FROM item i where i.status = ?1 ORDER by create_date desc", nativeQuery = true)
    Page<Item> findAllnewProduct(byte value, Pageable page);

    @Query(value = "SELECT count(*)  FROM item where status = ?1 ORDER by create_date desc", nativeQuery = true)
    Long countnewProduct(byte value);

    @Query(value = "SELECT i.* FROM item i INNER JOIN orderitem oi ON i.item_id=oi.item_id where i.status= ?1 group by i.item_id order by count(i.item_id) desc limit 10 ", nativeQuery = true)
    List<Item> findBestSell(byte value);

//    @Query(value = "SELECT i.* FROM item i INNER JOIN orderitem oi ON i.item_id=oi.item_id where i.status= ?1 group by i.item_id order by count(i.item_id) desc ", nativeQuery = true)
//    Page<Item> findAllBestSell(byte value, Pageable page);
    
    @Query(value = "SELECT * FROM item where status=?1 and item_id IN (?2) ", nativeQuery = true)
    Page<Item> findAllBestSell(byte value,List<Integer> itemIds, Pageable page);
    
    @Query(value = "SELECT count(*) from (SELECT count(*) FROM item i INNER JOIN orderitem oi ON i.item_id=oi.item_id where i.status= ?1 group by i.item_id order by count(i.item_id) desc) AS derived; ", nativeQuery = true)
    Long countBestSell(byte value);

    @Query(value = "SELECT * FROM item where item_unique_id=?1", nativeQuery = true)
    public List<Item> findAllByItemUniqeItemUniqueId(Integer itemUniqueId);

}
