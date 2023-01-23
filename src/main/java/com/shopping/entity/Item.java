/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.entity;

import com.shopping.form.ItemFeatureForm;
import com.shopping.form.ItemForm;
import java.util.Collection;
import java.util.Date;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author albinps
 */
@Entity
public class Item {

    public static enum Status {
        DELETED((byte) 0),
        ACTIVE((byte) 1);

        public final byte value;

        private Status(byte value) {
            this.value = value;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;
    private String name;
    private Float mrp;
    private Float price;
    private String image1;
    private String image2;
    private String image3;
    private String description;
    private byte status;
    private Integer stock;
    private Float discount;
    private Integer disper;
    private Float avgrate;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Quantity quantity_id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ItemUnique itemUnique;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Category category;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SubCatLevel1 subCatLevel1;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SubCatLevel2 subCatLevel2;

    @ElementCollection(fetch = FetchType.LAZY)
    private Collection<Features> features;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Information information;

    public Item() {
    }
    
    public Item(ItemForm form) {
        this.category = new Category(form.getCategoryId());
        this.name = form.getName();
        this.mrp = form.getMrp();
        this.price = form.getPrice();
        this.image1 = form.getImage1();
        this.image2 = form.getImage2();
        this.image3 = form.getImage3();
        this.description = form.getDescription();
        this.status = Item.Status.ACTIVE.value;
        this.stock = form.getStock();
        this.discount = form.getDiscount();
        int dis=Math.round((((this.mrp - this.price) + this.discount) / this.mrp) * 100);
        this.disper=(dis>100)?100:dis;
        Date dt = new Date();
        this.createDate = dt;
        this.updateDate = dt;
    }

    public Item(ItemFeatureForm form, Collection<Features> features, Information information, Category cat, SubCatLevel1 sub) {
        this.category = cat;
        this.subCatLevel1 = sub;
        this.name = form.getName();
        this.mrp = form.getMrp();
        this.price = form.getPrice();
        this.image1 = form.getImage1();
        this.image2 = form.getImage2();
        this.image3 = form.getImage3();
        this.description = form.getDescription();
        this.status = Item.Status.ACTIVE.value;
        this.stock = form.getStock();
        this.quantity_id = new Quantity(form.getQuantity_id());
        Date dt = new Date();
        this.createDate = dt;
        this.updateDate = dt;
        this.features = features;
        this.information = information;
        this.discount = form.getDiscount();
        int dis=Math.round((((this.mrp - this.price) + this.discount) / this.mrp) * 100);
        this.disper=(dis>100)?100:dis;
        this.subCatLevel2 = new SubCatLevel2(form.getSubCategoryLevel2Id());
        this.itemUnique=new ItemUnique(form.getItemUniqueId());
    }

    public Item(Integer itemId) {
        this.itemId = itemId;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public Collection<Features> getFeatures() {
        return features;
    }

    public void setFeatures(Collection<Features> features) {
        this.features = features;
    }

    public void setCategory(Category categoryId) {
        this.category = categoryId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public Category getCategory() {
        return category;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Float getMrp() {
        return mrp;
    }

    public void setMrp(Float mrp) {
        this.mrp = mrp;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public SubCatLevel1 getSubCatLevel1() {
        return subCatLevel1;
    }

    public void setSubCatLevel1(SubCatLevel1 subCatLevel1) {
        this.subCatLevel1 = subCatLevel1;
    }

    public Quantity getQuantity_id() {
        return quantity_id;
    }

    public void setQuantity_id(Quantity quantity_id) {
        this.quantity_id = quantity_id;
    }

    public SubCatLevel2 getSubCatLevel2() {
        return subCatLevel2;
    }

    public void setSubCatLevel2(SubCatLevel2 subCatLevel2) {
        this.subCatLevel2 = subCatLevel2;
    }

    public ItemUnique getItemUniqueId() {
        return itemUnique;
    }

    public void setItemUniqueId(ItemUnique itemUniqueId) {
        this.itemUnique = itemUniqueId;
    }

    public Integer getDisper() {
        return disper;
    }

    public void setDisper(Integer disper) {
        this.disper = disper;
    }

    public Float getAvgrate() {
        return avgrate;
    }

    public void setAvgrate(Float avgrate) {
        this.avgrate = avgrate;
    }
    
}
