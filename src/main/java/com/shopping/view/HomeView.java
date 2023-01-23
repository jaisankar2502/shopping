/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.view;

import java.util.List;

/**
 *
 * @author ajmal
 */
public class HomeView {
    
    private List<ItemView> bestOffers;
    private List<BannerView> bestOffersBanner;
    private List<ItemView> bestSell;
    private List<BannerView> bestSellBanner;
    private List<ItemView> newProduct;
    private List<BannerView> newProductBanner;

    public List<ItemView> getBestOffers() {
        return bestOffers;
    }

    public void setBestOffers(List<ItemView> bestOffers) {
        this.bestOffers = bestOffers;
    }

    public List<ItemView> getBestSell() {
        return bestSell;
    }

    public void setBestSell(List<ItemView> bestSell) {
        this.bestSell = bestSell;
    }

    public List<ItemView> getNewProduct() {
        return newProduct;
    }

    public void setNewProduct(List<ItemView> newProduct) {
        this.newProduct = newProduct;
    }  

    public List<BannerView> getBestOffersBanner() {
        return bestOffersBanner;
    }

    public void setBestOffersBanner(List<BannerView> bestOffersBanner) {
        this.bestOffersBanner = bestOffersBanner;
    }

    public List<BannerView> getBestSellBanner() {
        return bestSellBanner;
    }

    public void setBestSellBanner(List<BannerView> bestSellBanner) {
        this.bestSellBanner = bestSellBanner;
    }

    public List<BannerView> getNewProductBanner() {
        return newProductBanner;
    }

    public void setNewProductBanner(List<BannerView> newProductBanner) {
        this.newProductBanner = newProductBanner;
    }
    
    
    
}
