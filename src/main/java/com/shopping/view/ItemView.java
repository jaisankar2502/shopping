/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.view;

import com.shopping.entity.Item;
import java.util.Date;
import java.util.List;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author albinps
 */
public class ItemView {

    private final Integer itemId;
    private final String name;
    private final Float MRP;
    private final Float price;
    private final Integer discount;
    private final String image1;
    private final String image2;
    private final String image3;
    private final String description;
    private final Integer stock;
    private final byte itemstatus;
    @Temporal(TemporalType.TIMESTAMP)
    private final Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private final Date updateDate;
    private final Integer category_id;
    private final String category_name;
    private final Integer sub_cat_level1_id;
    private final String sub_cat_level1_name;
    private final Integer sub_cat_level2_id;
    private final String sub_cat_level2_name;
    private final Integer qty;
    private Boolean wishFlag;
    private List<ReviewView> review;
    private Float avgRate;

    public ItemView(Item item) {
        this.itemId = item.getItemId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.price = item.getPrice() - item.getDiscount();
        this.MRP = item.getMrp();
        this.category_id = item.getCategory().getCategoryId();
        this.category_name = item.getCategory().getName();
        this.sub_cat_level1_id = item.getSubCatLevel1().getSubcategoryId();
        this.sub_cat_level1_name = item.getSubCatLevel1().getName();
        this.sub_cat_level2_id = item.getSubCatLevel2().getSubCatLevel2Id();
        this.sub_cat_level2_name = item.getSubCatLevel2().getSubCatLevel2Name();
        this.createDate = item.getCreateDate();
        this.image1 = item.getImage1();
        this.image2 = item.getImage2();
        this.image3 = item.getImage3();
        this.stock = item.getStock();
        this.updateDate = item.getUpdateDate();
        this.qty = 1;
        this.discount = Math.round((((item.getMrp() - (item.getPrice() - item.getDiscount())) / item.getMrp()) * 100));
        this.itemstatus = item.getStatus();

    }

    public ItemView(Item item, boolean wishFlag) {
        this.itemId = item.getItemId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.price = item.getPrice() - item.getDiscount();
        this.MRP = item.getMrp();
        this.category_id = item.getCategory().getCategoryId();
        this.category_name = item.getCategory().getName();
        this.sub_cat_level1_id = item.getSubCatLevel1().getSubcategoryId();
        this.sub_cat_level1_name = item.getSubCatLevel1().getName();
        this.sub_cat_level2_id = item.getSubCatLevel2().getSubCatLevel2Id();
        this.sub_cat_level2_name = item.getSubCatLevel2().getSubCatLevel2Name();
        this.createDate = item.getCreateDate();
        this.image1 = item.getImage1();
        this.image2 = item.getImage2();
        this.image3 = item.getImage3();
        this.stock = item.getStock();
        this.updateDate = item.getUpdateDate();
        this.qty = 1;
        this.discount = Math.round((((item.getMrp() - (item.getPrice() - item.getDiscount())) / item.getMrp()) * 100));
        this.itemstatus = item.getStatus();
        this.wishFlag = wishFlag;

    }

    public ItemView(Item item, boolean wishFlag, List<ReviewView> review) {
        this.itemId = item.getItemId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.price = item.getPrice() - item.getDiscount();
        this.MRP = item.getMrp();
        this.category_id = item.getCategory().getCategoryId();
        this.category_name = item.getCategory().getName();
        this.sub_cat_level1_id = item.getSubCatLevel1().getSubcategoryId();
        this.sub_cat_level1_name = item.getSubCatLevel1().getName();
        this.sub_cat_level2_id = item.getSubCatLevel2().getSubCatLevel2Id();
        this.sub_cat_level2_name = item.getSubCatLevel2().getSubCatLevel2Name();
        this.createDate = item.getCreateDate();
        this.image1 = item.getImage1();
        this.image2 = item.getImage2();
        this.image3 = item.getImage3();
        this.stock = item.getStock();
        this.updateDate = item.getUpdateDate();
        this.qty = 1;
        this.discount = Math.round((((item.getMrp() - (item.getPrice() - item.getDiscount())) / item.getMrp()) * 100));
        this.itemstatus = item.getStatus();
        this.wishFlag = wishFlag;
        this.review = review;
        this.review = (review != null) ? review : null;
    }

    public ItemView(Item item, boolean wishFlag, List<ReviewView> review,Float avgRate) {
        this.itemId = item.getItemId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.price = item.getPrice() - item.getDiscount();
        this.MRP = item.getMrp();
        this.category_id = item.getCategory().getCategoryId();
        this.category_name = item.getCategory().getName();
        this.sub_cat_level1_id = item.getSubCatLevel1().getSubcategoryId();
        this.sub_cat_level1_name = item.getSubCatLevel1().getName();
        this.sub_cat_level2_id = item.getSubCatLevel2().getSubCatLevel2Id();
        this.sub_cat_level2_name = item.getSubCatLevel2().getSubCatLevel2Name();
        this.createDate = item.getCreateDate();
        this.image1 = item.getImage1();
        this.image2 = item.getImage2();
        this.image3 = item.getImage3();
        this.stock = item.getStock();
        this.updateDate = item.getUpdateDate();
        this.qty = 1;
        this.discount = Math.round((((item.getMrp() - (item.getPrice() - item.getDiscount())) / item.getMrp()) * 100));
        this.itemstatus = item.getStatus();
        this.wishFlag = wishFlag;
        this.review = review;
        this.review = (review != null) ? review : null;
        this.avgRate=(avgRate!=null)?avgRate:null;
    }

    public Float getMRP() {
        return MRP;
    }

    public String getImage2() {
        return image2;
    }

    public String getImage3() {
        return image3;
    }

    public Integer getStock() {
        return stock;
    }

    public Integer getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public String getImage1() {
        return image1;
    }

    public String getDescription() {
        return description;
    }

    public byte getItemstatus() {
        return itemstatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public Integer getSub_cat_level1_id() {
        return sub_cat_level1_id;
    }

    public String getSub_cat_level1_name() {
        return sub_cat_level1_name;
    }

    public Integer getSub_cat_level2_id() {
        return sub_cat_level2_id;
    }

    public String getSub_cat_level2_name() {
        return sub_cat_level2_name;
    }

    public Integer getDiscount() {
        return discount;
    }

    public Integer getQty() {
        return qty;
    }

    public Boolean getWishFlag() {
        return wishFlag;
    }

    public void setWishFlag(Boolean wishFlag) {
        this.wishFlag = wishFlag;
    }

    public List<ReviewView> getReview() {
        return review;
    }

    public void setReview(List<ReviewView> review) {
        this.review = review;
    }

    public Float getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(Float avgRate) {
        this.avgRate = avgRate;
    }

}
