/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.view;

import com.shopping.entity.Cart;
import com.shopping.entity.Item;
import com.shopping.json.Json;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ajmal
 */
public class ItemDetailsView {

    private Integer itemId;
    private String name;
    private Float MRP;
    private Float price;
    private String image1;
    private String image2;
    private String image3;
    private String description;
    private Integer stock;
    private byte status;
    private String weight;
    private Boolean cartFlag;
    private Integer cartCount;
    private Integer cartId;
    @Json.DateTimeFormat
    private Date createDate;
    @Json.DateTimeFormat
    private Date updateDate;
    private CategoryView category;
    private SubCategoryLevel1View subCategory;
    private Integer qty;
    private List<FeatureView> features;
    private InformationView informations;
    List<ItemQuantityView> ItemQuantity;
    private Integer discount;
    private boolean wishFlag;
    private List<ReviewView> reviews;
    private Float avgRate;

    public ItemDetailsView() {
    }

    public ItemDetailsView(Item item) {
        this.itemId = item.getItemId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.price = item.getPrice() - item.getDiscount();
        this.MRP = item.getMrp();
        this.category = (item.getCategory() == null ? new CategoryView() : new CategoryView(item.getCategory()));
        this.subCategory = (item.getSubCatLevel1() == null ? new SubCategoryLevel1View() : new SubCategoryLevel1View(item.getSubCatLevel1()));
        this.informations = (item.getInformation() == null ? new InformationView() : new InformationView(item.getInformation()));
        this.createDate = item.getCreateDate();
        this.image1 = item.getImage1();
        this.image2 = item.getImage2();
        this.image3 = item.getImage3();
        this.stock = item.getStock();
        this.updateDate = item.getUpdateDate();
        this.qty = 1;
        this.discount = Math.round((((item.getMrp() - (item.getPrice() - item.getDiscount())) / item.getMrp()) * 100));
        this.features = (item.getFeatures() != null)
                ? item.getFeatures().stream().map(f -> new FeatureView(f)).collect(Collectors.toList())
                : null;
    }

    public ItemDetailsView(Item item, List<ItemQuantityView> ItemQuantityList, Cart cart, boolean auth, boolean wishFlag) {
        this.itemId = item.getItemId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.price = item.getPrice() - item.getDiscount();
        this.MRP = item.getMrp();
        this.category = (item.getCategory() == null ? new CategoryView() : new CategoryView(item.getCategory()));
        this.subCategory = (item.getSubCatLevel1() == null ? new SubCategoryLevel1View() : new SubCategoryLevel1View(item.getSubCatLevel1()));
        this.informations = (item.getInformation() == null ? new InformationView() : new InformationView(item.getInformation()));
        this.createDate = item.getCreateDate();
        this.image1 = item.getImage1();
        this.image2 = item.getImage2();
        this.image3 = item.getImage3();
        this.stock = item.getStock();
        this.updateDate = item.getUpdateDate();
        this.discount = Math.round((((item.getMrp() - (item.getPrice() - item.getDiscount())) / item.getMrp()) * 100));
        this.wishFlag = wishFlag;
        if (auth == true) {
            this.cartFlag = (cart != null) ? true : false;
        } else {
            this.cartFlag = null;
        }
        this.cartCount = (cart != null) ? cart.getQty() : null;
        this.cartId = (cart != null) ? cart.getCartId() : null;
        this.qty = 1;
        this.weight = item.getQuantity_id().getQuantityName();
        this.ItemQuantity = ItemQuantityList;
        this.features = (item.getFeatures() != null)
                ? item.getFeatures().stream().map(f -> new FeatureView(f)).collect(Collectors.toList())
                : null;
    }

    public ItemDetailsView(Item item, List<ItemQuantityView> ItemQuantityList, Cart cart, boolean auth, boolean wishFlag, List<ReviewView> review) {
        this.itemId = item.getItemId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.price = item.getPrice() - item.getDiscount();
        this.MRP = item.getMrp();
        this.category = (item.getCategory() == null ? new CategoryView() : new CategoryView(item.getCategory()));
        this.subCategory = (item.getSubCatLevel1() == null ? new SubCategoryLevel1View() : new SubCategoryLevel1View(item.getSubCatLevel1()));
        this.informations = (item.getInformation() == null ? new InformationView() : new InformationView(item.getInformation()));
        this.createDate = item.getCreateDate();
        this.image1 = item.getImage1();
        this.image2 = item.getImage2();
        this.image3 = item.getImage3();
        this.stock = item.getStock();
        this.updateDate = item.getUpdateDate();
        this.discount = Math.round((((item.getMrp() - (item.getPrice() - item.getDiscount())) / item.getMrp()) * 100));
        this.wishFlag = wishFlag;
        if (auth == true) {
            this.cartFlag = (cart != null) ? true : false;
        } else {
            this.cartFlag = null;
        }
        this.cartCount = (cart != null) ? cart.getQty() : null;
        this.cartId = (cart != null) ? cart.getCartId() : null;
        this.qty = 1;
        this.weight = item.getQuantity_id().getQuantityName();
        this.ItemQuantity = ItemQuantityList;
        this.features = (item.getFeatures() != null)
                ? item.getFeatures().stream().map(f -> new FeatureView(f)).collect(Collectors.toList())
                : null;
        this.reviews=(review!=null)?review:null;
    }
     public ItemDetailsView(Item item, List<ItemQuantityView> ItemQuantityList, Cart cart, boolean auth, boolean wishFlag, List<ReviewView> review,Float avgRate) {
        this.itemId = item.getItemId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.price = item.getPrice() - item.getDiscount();
        this.MRP = item.getMrp();
        this.category = (item.getCategory() == null ? new CategoryView() : new CategoryView(item.getCategory()));
        this.subCategory = (item.getSubCatLevel1() == null ? new SubCategoryLevel1View() : new SubCategoryLevel1View(item.getSubCatLevel1()));
        this.informations = (item.getInformation() == null ? new InformationView() : new InformationView(item.getInformation()));
        this.createDate = item.getCreateDate();
        this.image1 = item.getImage1();
        this.image2 = item.getImage2();
        this.image3 = item.getImage3();
        this.stock = item.getStock();
        this.updateDate = item.getUpdateDate();
        this.discount = Math.round((((item.getMrp() - (item.getPrice() - item.getDiscount())) / item.getMrp()) * 100));
        this.wishFlag = wishFlag;
        if (auth == true) {
            this.cartFlag = (cart != null) ? true : false;
        } else {
            this.cartFlag = null;
        }
        this.cartCount = (cart != null) ? cart.getQty() : null;
        this.cartId = (cart != null) ? cart.getCartId() : null;
        this.qty = 1;
        this.weight = item.getQuantity_id().getQuantityName();
        this.ItemQuantity = ItemQuantityList;
        this.features = (item.getFeatures() != null)
                ? item.getFeatures().stream().map(f -> new FeatureView(f)).collect(Collectors.toList())
                : null;
        this.reviews=(review!=null)?review:null;
        this.avgRate=(avgRate!=null)?avgRate:null;
    }

    public Integer getItemId() {
        return itemId;
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

    public Float getMRP() {
        return MRP;
    }

    public void setMRP(Float MRP) {
        this.MRP = MRP;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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

    public CategoryView getCategory() {
        return category;
    }

    public void setCategory(CategoryView category) {
        this.category = category;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public List<FeatureView> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeatureView> features) {
        this.features = features;
    }

    public InformationView getInformations() {
        return informations;
    }

    public void setInformations(InformationView informations) {
        this.informations = informations;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public SubCategoryLevel1View getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategoryLevel1View subCategory) {
        this.subCategory = subCategory;
    }

    public List<ItemQuantityView> getItemQuantity() {
        return ItemQuantity;
    }

    public void setItemQuantity(List<ItemQuantityView> ItemQuantity) {
        this.ItemQuantity = ItemQuantity;
    }

    public String getWeight() {
        return weight;
    }

    public Boolean getCartFlag() {
        return cartFlag;
    }

    public void setCartFlag(Boolean cartFlag) {
        this.cartFlag = cartFlag;
    }

    public Integer getCartCount() {
        return cartCount;
    }

    public void setCartCount(Integer cartCount) {
        this.cartCount = cartCount;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public boolean isWishFlag() {
        return wishFlag;
    }

    public void setWishFlag(boolean wishFlag) {
        this.wishFlag = wishFlag;
    }

    public List<ReviewView> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewView> reviews) {
        this.reviews = reviews;
    }

    public Float getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(Float avgRate) {
        this.avgRate = avgRate;
    }
    
}
