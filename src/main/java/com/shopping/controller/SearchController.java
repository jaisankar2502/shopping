/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.controller;

import com.shopping.exception.BadRequestException;
import com.shopping.service.ItemService;
import com.shopping.util.LanguageUtil;
import com.shopping.util.Pager;
import com.shopping.view.HomeView;
import com.shopping.view.ItemView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author albinps
 */
@RestController
@RequestMapping("/item")
public class SearchController {

    @Autowired
    ItemService itemService;

    @Autowired
    private LanguageUtil languageUtil;

//    @GetMapping("/{offerId}")
//    public List<ItemView> getAllSubCategory(@PathVariable("offerId") int offerId){
//        return itemService.getAlloffersById(offerId);
//    
//    }
    @GetMapping("/home")
    public HomeView getAllSubCategory1() {
        return itemService.getAlloffersById();

    }

    @GetMapping("/bestoffers")
    public Pager<ItemView> getBestOffers(
            @RequestParam(value = "page", required = false, defaultValue = "1") String page,
            @RequestParam(value = "limit", required = false, defaultValue = "16") String limit
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
        return itemService.getBestOffers(pge, lmt);

    }

    @GetMapping("/bestsell")
    public Pager<ItemView> getBestSell(
            @RequestParam(value = "page", required = false, defaultValue = "1") String page,
            @RequestParam(value = "limit", required = false, defaultValue = "16") String limit
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
        return itemService.getBestSell(pge, lmt);

    }

    @GetMapping("/newproduct")
    public Pager<ItemView> getallNew(
            @RequestParam(value = "page", required = false, defaultValue = "1") String page,
            @RequestParam(value = "limit", required = false, defaultValue = "16") String limit
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
        return itemService.getallNew(pge, lmt);

    }

    @GetMapping("auth/home")
    public HomeView getAllAuthSubCategory1() {
        return itemService.getAlloffersById();

    }

}
