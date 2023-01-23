/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.service.impl;

import com.shopping.entity.Banner;
import com.shopping.entity.Cart;
import com.shopping.entity.Category;
import com.shopping.entity.Features;
import com.shopping.entity.Information;
import com.shopping.entity.Item;
import com.shopping.entity.Orderitem;
import com.shopping.entity.Review;
import com.shopping.entity.SubCatLevel1;
import com.shopping.entity.Wishlist;
import com.shopping.exception.BadRequestException;
import com.shopping.exception.NotFoundException;
import com.shopping.form.CartItemForm;
import com.shopping.form.ItemFeatureForm;
import com.shopping.form.ItemForm;
import com.shopping.repository.BannerRepository;
import com.shopping.repository.CartRepository;
import com.shopping.repository.CategoryRepository;
import com.shopping.repository.FeatureRepositry;
import com.shopping.repository.ItemRepository;
import com.shopping.service.ItemService;
import com.shopping.util.Pager;
import com.shopping.view.ItemDetailsView;
import com.shopping.view.ItemView;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.shopping.repository.InformationRepository;
import com.shopping.repository.OrderItemRepository;
import com.shopping.repository.ReviewRepository;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.shopping.repository.SubCategoryLevel1Repository;
import com.shopping.repository.WishListRepository;
import com.shopping.security.util.SecurityUtil;
import com.shopping.util.LanguageUtil;
import com.shopping.view.BannerView;
import com.shopping.view.HomeView;
import com.shopping.view.ItemQuantityView;
import com.shopping.view.ReviewView;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.data.domain.Sort;

/**
 *
 * @author albinps
 */
@Service
@Transactional
public class ItemServiceImp implements ItemService {

    private static final Logger log = LoggerFactory.getLogger(ItemServiceImp.class);

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FeatureRepositry featuresRepository;
    @Autowired
    private SubCategoryLevel1Repository subCategoryRepository;
    @Autowired
    private InformationRepository informationRepositry;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private WishListRepository wishListRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private LanguageUtil languageUtil;
    @PersistenceContext
    @Autowired
    private EntityManager em;

    @Override
    public ItemView add(ItemForm form) {

        return new ItemView(itemRepository.save(new Item(form)));
    }

    public List<ItemView> list(Integer category) {
        return StreamSupport.stream(itemRepository
                .findAllByCategoryCategoryId(category).spliterator(), false)
                .map(item -> new ItemView(item))
                .collect(Collectors.toList());
    }

    @Override
    public ItemDetailsView get(Integer itemId, Boolean auth) {
        return itemRepository.findByItemId(itemId)
                .map((item) -> {
                    Integer itemUniqueId = item.getItemUniqueId().getItemUniqueId();
                    List<ItemQuantityView> itemUniqueIdList = StreamSupport.stream(itemRepository.findAllByItemUniqeItemUniqueId(itemUniqueId).spliterator(), false)
                            .map(qtny -> new ItemQuantityView(qtny))
                            .collect(Collectors.toList());
                    Cart cart = cartRepository.findByUserUserIdAndItemAndStatus(SecurityUtil.getCurrentUserId(), item,Cart.Status.CART.value);
                    Wishlist wishlist = wishListRepository.findByItemItemIdAndUserUserIdAndStatus(item.getItemId(), SecurityUtil.getCurrentUserId(), Wishlist.Status.ACTIVE.value);

                    List<ReviewView> review = StreamSupport.stream(reviewRepository
                            .findByItemIdAndStatus(item.getItemId(), Review.Status.ACTIVE.value).spliterator(), false)
                            .map(rev -> new ReviewView(rev))
                            .collect(Collectors.toList());
                    Float avgRate = reviewRepository.getAverageRate(item.getItemId(), Review.Status.ACTIVE.value);
                    if (wishlist != null) {
                        return new ItemDetailsView(item, itemUniqueIdList, cart, auth, true, review, avgRate);

                    } else {
                        return new ItemDetailsView(item, itemUniqueIdList, cart, auth, false, review, avgRate);

                    }
                }).orElseThrow(NotFoundException::new);
    }

//    @Override
//    public Pager<ItemView> listItem(String search, Integer limit, String sort, boolean type, Integer page, Integer subcatId) {
//        Pager<ItemView> itemPager;
//        Long queryCount = null;
//        List<ItemView> itemList;
//        queryCount = itemRepository.countItemList(Item.Status.ACTIVE.value, search,subcatId);
//
//        itemList = StreamSupport.stream(itemRepository
//                .getItemList(Item.Status.ACTIVE.value, search,subcatId,
//                        PageRequest.of(page - 1, limit, (type == true) ? Sort.Direction.DESC : Sort.Direction.ASC, sort)).spliterator(), false)
//                .map(inquiry -> new ItemView(inquiry))
//                .collect(Collectors.toList());
//
//        itemPager = new Pager(limit, queryCount.intValue(), page);
//        itemPager.setResult(itemList);
//        return itemPager;
//
//    }
    @Override
    public List<ItemView> getItems(CartItemForm form) {
        return StreamSupport.stream(itemRepository
                .findByItemIdIn(form.getItemId()).spliterator(), false)
                .map(cart -> new ItemView(cart))
                .collect(Collectors.toList());

    }

    @Override
    public ItemDetailsView addItemFeature(ItemFeatureForm form) {
        Collection<Features> featureList = null;
        if (form.getFeatures() != null) {
            featureList = featuresRepository.findByStatusAndFeaturesIdIn(Features.Status.ACTIVE.value, form.getFeatures());
        }
        if (form.getCategoryId() != null) {

        }

        Information info = informationRepositry.save(new Information(form.getInformations()));
        Category category = categoryRepository.findById(form.getCategoryId()).orElseThrow(() -> new NotFoundException("Id not found"));
        SubCatLevel1 subCategory = subCategoryRepository.findById(form.getSubCategoryLevel1Id()).orElseThrow(() -> new NotFoundException("Id not found"));
        return new ItemDetailsView(itemRepository.save(new Item(form, featureList, info, category, subCategory)));

    }

    @Override
    public void updateItem(Integer itemId, ItemFeatureForm form) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new NotFoundException("Id not found"));
        Information info = null;
        if (item != null) {
            if (form.getCategoryId() != null) {
                Category category = categoryRepository.findById(form.getCategoryId()).orElseThrow(() -> new NotFoundException("Id not found"));
                if (category != null) {
                    item.setCategory(category);
                }

            }
            if (form.getSubCategoryLevel1Id() != null) {
                SubCatLevel1 subCategory = subCategoryRepository.findById(form.getSubCategoryLevel1Id()).orElseThrow(() -> new NotFoundException("Id not found"));
                if (subCategory != null) {
                    item.setSubCatLevel1(subCategory);
                }
            }
            if (form.getFeatures() != null) {
                Collection<Features> featureList = featuresRepository.findByStatusAndFeaturesIdIn(Features.Status.ACTIVE.value, form.getFeatures());
                item.setFeatures(featureList);
            }
            item.setUpdateDate(new Date());
            item.setStock(form.getStock());
            item.setDescription(form.getDescription());
            item.setImage1(form.getImage1());
            item.setImage2(form.getImage2());
            item.setImage3(form.getImage3());
            item.setMrp(form.getMrp());
            item.setName(form.getName());
            item.setPrice(form.getPrice());

            info = informationRepositry.findByInformationId(item.getInformation().getInformationId()).orElseThrow(() -> new NotFoundException("Id not found"));
            info.setUpdatedDate(new Date());
            info.setBrand(form.getInformations().getBrand());
            info.setCountry(form.getInformations().getCountry());
            info.setManufacturer(form.getInformations().getManufacturer());
            info = informationRepositry.save(info);
            item.setInformation(info);
            itemRepository.save(item);

        }
    }

    @Override
    public void addStock(Integer itemId, Integer stock) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new NotFoundException("Id not found"));
        item.setStock(item.getStock() + stock);
        item.setUpdateDate(new Date());
        itemRepository.save(item);
    }

    @Override
    public Pager<ItemView> listItem(String search, Integer lmt, String sort, boolean type, Integer pge,
            Integer subcatId, String lastId, Integer mindis, Integer maxdis, Integer minprice, Integer maxprice, Integer rate) {

        Pager<ItemView> itemPager;
        List<ItemView> itemlist;

        if (pge < 1) {
            throw new BadRequestException(languageUtil.getTranslatedText("page.count.invalid", null, "en"));
        }
        if (lmt < 1) {
            throw new BadRequestException(languageUtil.getTranslatedText("limit.count.invalid", null, "en"));
        }

        Long count;
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
        Root<Item> itemroot = criteriaQuery.from(Item.class);
        List<Predicate> predicate = new ArrayList<>();

        if (!StringUtils.isEmpty(search)) {
            Predicate predicateSearch2 = criteriaBuilder.like(itemroot.get("name"), "%" + search + "%");

            Predicate predicateSearch1 = criteriaBuilder.like(itemroot.get("category").get("name"), "%" + search + "%");
            Predicate predicateSearch = criteriaBuilder.or(predicateSearch2, predicateSearch1);
            predicate.add(predicateSearch);

        }
        if (minprice != null && maxprice != null) {

            Predicate predicatediscount = criteriaBuilder
                    .between(itemroot.get("price"), (float) minprice, (float) maxprice);
            predicate.add(predicatediscount);
        }

        if (rate != null) {
            Predicate predicatediscount = criteriaBuilder
                    .greaterThanOrEqualTo(itemroot.get("avgrate"), (float) rate);
            predicate.add(predicatediscount);
        }

        if (mindis != null && maxdis != null) {

            Predicate predicatediscount = criteriaBuilder
                    .between(itemroot.get("disper"), mindis, maxdis);
            predicate.add(predicatediscount);
        }

        if (subcatId != null) {
            Predicate predicateCategory = criteriaBuilder.equal(itemroot.get("subCatLevel1").get("subCatLevel1Id"), subcatId);
            predicate.add(predicateCategory);
        }

        if (lastId != null) {

            Predicate predicateLastid = criteriaBuilder.lessThan(itemroot.get("itemId"), Integer.parseInt(lastId));
            predicate.add(predicateLastid);
        }

        Predicate And = criteriaBuilder.and(predicate.toArray(new Predicate[predicate.size()]));
        criteriaQuery.where(And);

        if (sort != null) {
            if (itemFieldExists(sort)) {
                if (type) {
                    criteriaQuery.orderBy(criteriaBuilder.asc(itemroot.get(sort)));
                } else {
                    criteriaQuery.orderBy(criteriaBuilder.desc(itemroot.get(sort)));
                }

            } else {
                throw new BadRequestException(languageUtil.getTranslatedText("item.invalid.sort", null, "en"));
            }
        }

        TypedQuery<Item> list = em.createQuery(criteriaQuery);
        list.setFirstResult((pge - 1) * lmt);
        list.setMaxResults(lmt);
        CriteriaQuery<Long> cqCount = criteriaBuilder.createQuery(Long.class);
        Root<Item> itemcntqury = cqCount.from(criteriaQuery.getResultType());
        cqCount.where(And);
        cqCount.select(criteriaBuilder.count(itemcntqury));
        count = em.createQuery(cqCount).getSingleResult();

        Page<Item> result = new PageImpl<>(list.getResultList(),
                PageRequest.of(pge - 1, lmt), count);

        itemlist = StreamSupport
                .stream(result.spliterator(), false)
                .map((item) -> {
                    Wishlist wishlist = wishListRepository.findByItemItemIdAndUserUserIdAndStatus(item.getItemId(), SecurityUtil.getCurrentUserId(), Wishlist.Status.ACTIVE.value);
                    List<ReviewView> review = StreamSupport.stream(reviewRepository
                            .findByItemIdAndStatus(item.getItemId(), Review.Status.ACTIVE.value).spliterator(), false)
                            .map(rev -> new ReviewView(rev))
                            .collect(Collectors.toList());
                    if (wishlist != null) {
                        return new ItemView(item, true, review);
                    } else {
                        return new ItemView(item, false, review);
                    }
                }).collect(Collectors.toList());
        itemPager = new Pager<>(lmt, count.intValue(), pge);
        itemPager.setResult(itemlist);

        return itemPager;

    }

//    @Override
//    public List<ItemView> getAlloffersById(int offerId){
//        if(offerId==1){
//             return StreamSupport.stream(itemRepository
//                .findBestoffers(Item.Status.ACTIVE.value).spliterator(), false)
//                .map(item -> new ItemView(item))
//                .collect(Collectors.toList());
//        }
//        else if (offerId==2){
//            return StreamSupport.stream(itemRepository
//                .findBestSell(Item.Status.ACTIVE.value).spliterator(), false)
//                .map(item -> new ItemView(item))
//                .collect(Collectors.toList());
//        }else if(offerId==3){
//             return StreamSupport.stream(itemRepository
//                .findnewProduct(Item.Status.ACTIVE.value).spliterator(), false)
//                .map(item -> new ItemView(item))
//                .collect(Collectors.toList());
//        }
//
//        else{
//            return  null;
//        }
//
//    }
    @Override
    public HomeView getAlloffersById() {
        HomeView homeView = new HomeView();
        List<ItemView> bestoffers = StreamSupport.stream(itemRepository
                .findBestoffers(Item.Status.ACTIVE.value).spliterator(), false)
                .map((item) -> {
                    Wishlist wishlist = wishListRepository.findByItemItemIdAndUserUserIdAndStatus(item.getItemId(), SecurityUtil.getCurrentUserId(), Wishlist.Status.ACTIVE.value);
                    List<ReviewView> review = StreamSupport.stream(reviewRepository
                            .findByItemIdAndStatus(item.getItemId(), Review.Status.ACTIVE.value).spliterator(), false)
                            .map(rev -> new ReviewView(rev))
                            .collect(Collectors.toList());
                    Float avgRate = reviewRepository.getAverageRate(item.getItemId(), Review.Status.ACTIVE.value);
                    if (wishlist != null) {
                        return new ItemView(item, true, review, avgRate);
                    } else {
                        return new ItemView(item, false, review, avgRate);
                    }
                })
                .collect(Collectors.toList());

        List<BannerView> bestOffersBanner = StreamSupport.stream(
                bannerRepository.findAllByStatusAndType(Banner.Status.ACTIVE.value, Banner.Type.OFFER.value).spliterator(),
                false).map((banner) -> new BannerView(banner)).collect(Collectors.toList());

        List<ItemView> bestSell = StreamSupport.stream(itemRepository
                .findBestSell(Item.Status.ACTIVE.value).spliterator(), false)
                .map((item) -> {
                    Wishlist wishlist = wishListRepository.findByItemItemIdAndUserUserIdAndStatus(item.getItemId(), SecurityUtil.getCurrentUserId(), Wishlist.Status.ACTIVE.value);
                    List<ReviewView> review = StreamSupport.stream(reviewRepository
                            .findByItemIdAndStatus(item.getItemId(), Review.Status.ACTIVE.value).spliterator(), false)
                            .map(rev -> new ReviewView(rev))
                            .collect(Collectors.toList());
                    Float avgRate = reviewRepository.getAverageRate(item.getItemId(), Review.Status.ACTIVE.value);
                    if (wishlist != null) {
                        return new ItemView(item, true, review, avgRate);
                    } else {
                        return new ItemView(item, false, review, avgRate);
                    }
                })
                .collect(Collectors.toList());

        List<BannerView> bestSellBanner = StreamSupport.stream(
                bannerRepository.findAllByStatusAndType(Banner.Status.ACTIVE.value, Banner.Type.SELL.value).spliterator(),
                false).map((banner) -> new BannerView(banner)).collect(Collectors.toList());

        List<ItemView> newProduct = StreamSupport.stream(itemRepository
                .findnewProduct(Item.Status.ACTIVE.value).spliterator(), false)
                .map((item) -> {
                    Wishlist wishlist = wishListRepository.findByItemItemIdAndUserUserIdAndStatus(item.getItemId(), SecurityUtil.getCurrentUserId(), Wishlist.Status.ACTIVE.value);
                    List<ReviewView> review = StreamSupport.stream(reviewRepository
                            .findByItemIdAndStatus(item.getItemId(), Review.Status.ACTIVE.value).spliterator(), false)
                            .map(rev -> new ReviewView(rev))
                            .collect(Collectors.toList());
                    Float avgRate = reviewRepository.getAverageRate(item.getItemId(), Review.Status.ACTIVE.value);
                    if (wishlist != null) {
                        return new ItemView(item, true, review, avgRate);
                    } else {
                        return new ItemView(item, false, review, avgRate);
                    }
                })
                .collect(Collectors.toList());

        List<BannerView> newProductBanner = StreamSupport.stream(
                bannerRepository.findAllByStatusAndType(Banner.Status.ACTIVE.value, Banner.Type.NEW.value).spliterator(),
                false).map((banner) -> new BannerView(banner)).collect(Collectors.toList());

        homeView.setBestOffers((bestoffers != null) ? bestoffers : null);
        homeView.setBestOffersBanner((bestOffersBanner != null) ? bestOffersBanner : null);
        homeView.setBestSell((bestSell != null) ? bestSell : null);
        homeView.setBestSellBanner((bestSellBanner != null) ? bestSellBanner : null);
        homeView.setNewProduct((newProduct != null) ? newProduct : null);
        homeView.setNewProductBanner((newProductBanner != null) ? newProductBanner : null);

        return homeView;

    }

    @Override
    public Pager<ItemView> getBestOffers(Integer page, Integer limit) {

        Pager<ItemView> itemPager;
        Long queryCount = null;
        List<ItemView> itemList;
        queryCount = itemRepository.countAllBestoffers(Item.Status.ACTIVE.value);

        itemList = StreamSupport.stream(itemRepository
                .findAllBestoffers(Item.Status.ACTIVE.value,
                        PageRequest.of(page - 1, limit)).spliterator(), false)
                .map((item) -> {
                    Wishlist wishlist = wishListRepository.findByItemItemIdAndUserUserIdAndStatus(item.getItemId(), SecurityUtil.getCurrentUserId(), Wishlist.Status.ACTIVE.value);
                    List<ReviewView> review = StreamSupport.stream(reviewRepository
                            .findByItemIdAndStatus(item.getItemId(), Review.Status.ACTIVE.value).spliterator(), false)
                            .map(rev -> new ReviewView(rev))
                            .collect(Collectors.toList());
                    Float avgRate = reviewRepository.getAverageRate(item.getItemId(), Review.Status.ACTIVE.value);
                    if (wishlist != null) {
                        return new ItemView(item, true, review, avgRate);
                    } else {
                        return new ItemView(item, false, review, avgRate);
                    }
                })
                .collect(Collectors.toList());

        itemPager = new Pager(limit, queryCount.intValue(), page);
        itemPager.setResult(itemList);
        return itemPager;
    }

    @Override
    public Pager<ItemView> getallNew(Integer page, Integer limit) {
        Pager<ItemView> itemPager;
        Long queryCount = null;
        List<ItemView> itemList;
        queryCount = itemRepository.countnewProduct(Item.Status.ACTIVE.value);

        itemList = StreamSupport.stream(itemRepository
                .findAllnewProduct(Item.Status.ACTIVE.value,
                        PageRequest.of(page - 1, limit)).spliterator(), false)
                .map((item) -> {
                    Wishlist wishlist = wishListRepository.findByItemItemIdAndUserUserIdAndStatus(item.getItemId(), SecurityUtil.getCurrentUserId(), Wishlist.Status.ACTIVE.value);
                    List<ReviewView> review = StreamSupport.stream(reviewRepository
                            .findByItemIdAndStatus(item.getItemId(), Review.Status.ACTIVE.value).spliterator(), false)
                            .map(rev -> new ReviewView(rev))
                            .collect(Collectors.toList());
                    Float avgRate = reviewRepository.getAverageRate(item.getItemId(), Review.Status.ACTIVE.value);
                    if (wishlist != null) {
                        return new ItemView(item, true, review, avgRate);
                    } else {
                        return new ItemView(item, false, review, avgRate);
                    }
                })
                .collect(Collectors.toList());

        itemPager = new Pager(limit, queryCount.intValue(), page);
        itemPager.setResult(itemList);
        return itemPager;
    }

    @Override
    public Pager<ItemView> getBestSell(Integer page, Integer limit) {
        Pager<ItemView> itemPager;
        Long queryCount = null;
        List<ItemView> itemList;
//        queryCount = itemRepository.countBestSell(Item.Status.ACTIVE.value);
        List<Integer> odrerItems = orderItemRepository.findAllBestOffersByStatus(Item.Status.ACTIVE.value);
        if (odrerItems == null) {
            throw new NullPointerException(languageUtil.getTranslatedText("invalid.order.item", null, "en"));
        }
        queryCount = (long) odrerItems.size();
        itemList = StreamSupport.stream(itemRepository
                .findAllBestSell(Item.Status.ACTIVE.value, odrerItems,
                        PageRequest.of(page - 1, limit)).spliterator(), false)
                .map((item) -> {
                    Wishlist wishlist = wishListRepository.findByItemItemIdAndUserUserIdAndStatus(item.getItemId(), SecurityUtil.getCurrentUserId(), Wishlist.Status.ACTIVE.value);
                    List<ReviewView> review = StreamSupport.stream(reviewRepository
                            .findByItemIdAndStatus(item.getItemId(), Review.Status.ACTIVE.value).spliterator(), false)
                            .map(rev -> new ReviewView(rev))
                            .collect(Collectors.toList());
                    Float avgRate = reviewRepository.getAverageRate(item.getItemId(), Review.Status.ACTIVE.value);
                    if (wishlist != null) {
                        return new ItemView(item, true, review, avgRate);
                    } else {
                        return new ItemView(item, false, review, avgRate);
                    }
                })
                .collect(Collectors.toList());

        itemPager = new Pager(limit, queryCount.intValue(), page);
        itemPager.setResult(itemList);
        return itemPager;
    }

    public static enum itemSortfields {
        CREATED_DATE("createDate"), UPDATED_DATE("updateDate"), ITEM_ID("itemId"), NAME("name"), PRICE("price");
        public String value;

        private itemSortfields(String value) {
            this.value = value;
        }
    }

    public static boolean itemFieldExists(String sort) {
        for (ItemServiceImp.itemSortfields c : ItemServiceImp.itemSortfields.values()) {
            if (c.value.equals(sort)) {
                return true;
            }
        }
        return false;
    }

}
