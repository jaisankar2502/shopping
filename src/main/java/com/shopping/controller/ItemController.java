/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.controller;

import com.shopping.exception.BadRequestException;
import com.shopping.form.CartItemForm;
import com.shopping.form.ItemFeatureForm;
import com.shopping.service.ItemService;
import com.shopping.util.LanguageUtil;
import com.shopping.util.Pager;
import com.shopping.view.ItemDetailsView;
import com.shopping.view.ItemView;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author albinps
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private LanguageUtil languageUtil;

    @Autowired
    private ItemService itemService;

//    @PostMapping
//    public ItemView add(@Valid @RequestBody ItemForm form) {
//        return itemService.add(form);
//    }
    @GetMapping
    public List<ItemView> list(
            @RequestParam(value = "category", required = false, defaultValue = "") Integer category) {
        return itemService.list(category);
    }
//    @GetMapping("/{itemId}")
//    public ItemView get(@PathVariable("itemId") Integer itemId) {
//        return itemService.get(itemId);
//    }

    @GetMapping("/{itemId}")
    public ItemDetailsView get(@PathVariable("itemId") Integer itemId, HttpServletRequest request) {
        boolean flag = false;
        if (request.getHeader("Authorization") != null && request.getHeader("Authorization").contains("Shopping")) {
            flag = true;
        }
        return itemService.get(itemId, flag);
    }

    @GetMapping("/auth/{itemId}")
    public ItemDetailsView getAuthItem(@PathVariable("itemId") Integer itemId) {
        return itemService.get(itemId, true);
    }

//    @GetMapping("/list")
//    public Pager<ItemView> list(
//            @RequestParam(value = "search", required = false) String search,
//            @RequestParam(value = "page", required = false) Integer page,
//            @RequestParam(value = "limit", required = false) Integer limit,
//            @RequestParam(value = "sort", required = false) String sort,
//            @RequestParam(value = "type", required = false) Boolean type,
//            @RequestParam(value = "subcatId", required = false) Integer subcatId) {
//        if (search == null) {
//            search = "";
//        }
//
//        if (limit == null) {
//            limit = 12;
//        }
//        if (page == null) {
//            page = 1;
//        }
//        if (null == sort) {
//        sort = "update_date";
//        }
//        if (null == type) {
//            type = true;
//        }
//        return itemService.listItem(search, limit, sort, type, page,subcatId);
//    }
    @GetMapping("/list")
    public Pager<ItemView> list(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "page", required = false, defaultValue = "1") String page,
            @RequestParam(value = "limit", required = false, defaultValue = "16") String limit,
            @RequestParam(value = "sort", required = false, defaultValue = "updateDate") String sort,
            @RequestParam(value = "type", required = false, defaultValue = "false") boolean type,
            @RequestParam(value = "subcatId", required = false) Integer subcatId,
            @RequestParam(value = "lastId", required = false) String lastId,
            @RequestParam(value = "mindis", required = false) Integer mindis,
            @RequestParam(value = "maxdis", required = false) Integer maxdis,
            @RequestParam(value = "minprice", required = false) Integer minprice,
            @RequestParam(value = "maxprice", required = false) Integer maxprice,
            @RequestParam(value = "rate", required = false) Integer rate
    ) {

        Integer lmt = null;

        Integer pge = null;

        try {
            lmt = Integer.parseInt(limit);
        } catch (NumberFormatException e) {
            throw new BadRequestException(languageUtil.getTranslatedText("invalid.limit.option", null, "en"));
        }

        try {
            pge = Integer.parseInt(page);
        } catch (NumberFormatException e) {
            throw new BadRequestException(languageUtil.getTranslatedText("invalid.page.option", null, "en"));
        }

        return itemService.listItem(search, lmt, sort, type, pge, subcatId, lastId, mindis, maxdis, minprice, maxprice,rate);
    }

    @PostMapping("/cart")
    public List<ItemView> getItems(@Valid @RequestBody CartItemForm form) {
        return itemService.getItems(form);
    }

    @PostMapping
    public ItemDetailsView addItemFeature(@RequestBody @Valid ItemFeatureForm form) {
        return itemService.addItemFeature(form);

    }

    @PutMapping("/{itemId}")
    public void updateItem(
            @PathVariable(name = "itemId") Integer itemId,
            @RequestBody ItemFeatureForm form
    ) {
        itemService.updateItem(itemId, form);
    }

    @PutMapping("/addStock/{itemId}")
    public void addStock(
            @PathVariable(name = "itemId") Integer itemId,
            @RequestParam(value = "stock", required = false, defaultValue = "") Integer stock
    ) {
        itemService.addStock(itemId, stock);
    }

}
