/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.service;

import com.shopping.form.CartItemForm;
import com.shopping.form.ItemFeatureForm;
import com.shopping.form.ItemForm;
import com.shopping.util.Pager;
import com.shopping.view.HomeView;
import com.shopping.view.ItemDetailsView;
import com.shopping.view.ItemView;
import java.util.List;

/**
 *
 * @author albinps
 */
public interface ItemService {

   // public Collection<User> list();

    public ItemView add(ItemForm form);

    public  List<ItemView> list(Integer category);

    public ItemDetailsView get(Integer itemId,Boolean auth);

//    public Pager<ItemView> listItem(String search, Integer limit, String sort, Boolean type, Integer page,Integer subcatId);

    public List<ItemView> getItems(CartItemForm form);

    public ItemDetailsView addItemFeature(ItemFeatureForm form);

    public void updateItem(Integer itemId, ItemFeatureForm form);

    public void addStock(Integer itemId, Integer stock);

//    public List<ItemView> getAlloffersById(int offerId);
    
    public Pager<ItemView> listItem(String search, Integer lmt, String sort, boolean type,
            Integer pge, Integer subcatId,String lastId,Integer mindis,Integer maxdis, Integer minprice, Integer maxprice,Integer rate);

    public HomeView getAlloffersById();

    public Pager<ItemView> getBestOffers(Integer page, Integer limit);

    public Pager<ItemView> getallNew(Integer pge, Integer lmt);

    public Pager<ItemView> getBestSell(Integer pge, Integer lmt);
    
}
