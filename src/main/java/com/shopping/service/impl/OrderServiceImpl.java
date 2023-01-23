/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.service.impl;

import com.shopping.entity.Address;
import com.shopping.entity.Cart;
import com.shopping.entity.Coupon;
import com.shopping.entity.Item;
import com.shopping.entity.Orderitem;
import com.shopping.entity.Orderitem_;
import com.shopping.entity.Orders;
import com.shopping.entity.Orders_;
import com.shopping.entity.Payment;
import com.shopping.entity.Shipping;
import com.shopping.entity.Track;
import com.shopping.entity.User;
import com.shopping.exception.BadRequestException;
import com.shopping.exception.NotFoundException;
import com.shopping.form.OrderForm;
import com.shopping.form.OrderItemData;
import com.shopping.repository.CartRepository;
import com.shopping.repository.CouponRepository;
import com.shopping.repository.ItemRepository;
import com.shopping.repository.OrderItemRepository;
import com.shopping.repository.OrdersRepository;
import com.shopping.repository.PaymentRepository;
import com.shopping.repository.ShippingRepository;
import com.shopping.repository.TrackRepository;
import com.shopping.repository.UserRepository;
import com.shopping.repository.UsersAddressRepository;
import com.shopping.security.util.DatetoTimeStampUtil;
import com.shopping.security.util.SecurityUtil;
import com.shopping.service.OrderService;
import com.shopping.util.LanguageUtil;
import com.shopping.util.MailServiceUtil;
import com.shopping.util.Pager;
import com.shopping.view.ItemOrderDetailsView;
import com.shopping.view.OrderDetailView;
import com.shopping.view.OrderStatusView;
import com.shopping.view.OrderView;
import com.shopping.view.ResponseView;
import com.shopping.view.TrackView;
import com.stripe.Stripe;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.RateLimitException;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.param.CustomerCreateParams;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 *
 * @author ajmal
 */
@Service
public class OrderServiceImpl implements OrderService {

    Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Value("${stripe.apiKey}")
    private String stripeApiKey;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UsersAddressRepository addressRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShippingRepository shippingRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private MailServiceUtil mailServiceUtil;

    @Autowired
    private LanguageUtil languageUtil;

    @PersistenceContext
    @Autowired
    private EntityManager em;

    private final Byte[] shipping = {};

    @Override
    @Transactional
    public ResponseView addOrder(OrderForm form) {
        ResponseView v = new ResponseView();
        boolean flag = true;
        Item item = new Item();
        User user = new User();
        Orders order = new Orders();
        Track track = new Track();

        float amount = 0;
        List<Integer> itemIds = new ArrayList<>();
        for (OrderItemData orderItem : form.getOrderItem()) {
            item = itemRepository.findByItemIdAndStatus(orderItem.getItemId(), Item.Status.ACTIVE.value)
                    .orElseThrow(() -> new NotFoundException(languageUtil.getTranslatedText("item.notfound", null, "en")));
            if (item.getStock() < orderItem.getQnty()) {
                throw new NotFoundException(languageUtil.getTranslatedText("item.notavailable", null, "en"));
            }
            itemIds.add(orderItem.getItemId());
            amount = amount + item.getPrice() * orderItem.getQnty();
            item.setStock(item.getStock() - orderItem.getQnty());
            item.setUpdateDate(new Date());
            itemRepository.save(item);

        }
        Payment payment = new Payment();

        switch (form.getPaymentMethord()) {

            case 1:
                payment.setPaymentType(Payment.PaymentType.CARD.value);
                break;
            case 2:
                payment.setPaymentType(Payment.PaymentType.CASH.value);
                break;
            case 3:
                payment.setPaymentType(Payment.PaymentType.UPI.value);
                break;
            case 4:
                payment.setPaymentType(Payment.PaymentType.BANK.value);
            default:
                payment.setPaymentType(Payment.PaymentType.EMPTY.value);
                break;

        }
        payment.setStatus(Payment.Status.INPROGRESS.value);
        payment.setCreatedDate(new Date());
        payment.setUpdatedDate(new Date());
        payment = paymentRepository.save(payment);

        if (payment != null) {
            Coupon coupon = null;
            if (form.getCouponId() != null) {
                coupon = couponRepository.findByCouponIdAndStatus(form.getCouponId(), Coupon.Status.ACTIVE.value)
                        .orElseThrow(() -> new NotFoundException(languageUtil.getTranslatedText("coupon.notfound", null, "en")));
                int count = (coupon.getCount() != null) ? coupon.getCount() : 0;
                if (count + 1 < coupon.getLim()) {
                    coupon.setCount(count + 1);
                    coupon.setUpdatedDate(new Date());
                } else {
                    coupon.setCount(count + 1);
                    coupon.setStatus(Coupon.Status.COUNTEXIDED.value);
                    coupon.setUpdatedDate(new Date());
                }
                couponRepository.save(coupon);

            }
            Address address = addressRepository.findByAddressIdAndUserUserIdAndStatus(
                    form.getAddrId(),
                    SecurityUtil.getCurrentUserId(), Address.Status.ACTIVE.value)
                    .orElseThrow(() -> new NotFoundException(languageUtil.getTranslatedText("address.notfound", null, "en")));
            user = userRepository.findById(SecurityUtil.getCurrentUserId())
                    .orElseThrow(() -> new NotFoundException(languageUtil.getTranslatedText("user.notfound", null, "en")));

            order.setUser(user);
            order.setAddr(address);
            order.setPayment(payment);
            order.setCreatedDate(new Date());
            order.setUpdatedDate(new Date());
            order.setNotes(form.getNotes());
            order.setCoupon((coupon != null) ? coupon : null);
            float discount = (float) ((coupon != null) ? coupon.getAmount() : 0);
            order.setDiscount(discount);
            amount = (coupon != null)
                    ? (amount - coupon.getAmount()) < 0 ? 0 : amount - coupon.getAmount()
                    : amount;
            order.setAmount(amount);
            order.setStatus(Orders.Status.PENDING.value);
            order = ordersRepository.save(order);

            if (order != null) {
                for (OrderItemData OrderItemdata : form.getOrderItem()) {
                    item = itemRepository.findByItemIdAndStatus(OrderItemdata.getItemId(), Item.Status.ACTIVE.value)
                            .orElseThrow(() -> new NotFoundException(languageUtil.getTranslatedText("item.notfound", null, "en")));
                    Shipping shipping = new Shipping();
                    shipping.setStatus(Shipping.Status.ORDERED.value);
                    shipping.setCreatedDate(new Date());
                    shipping.setUpdatedDate(new Date());
                    shipping = shippingRepository.save(shipping);

                    Orderitem orderItem = new Orderitem();
                    orderItem.setItem(item);
                    orderItem.setOrders(order);
                    orderItem.setShipping(shipping);
                    orderItem.setAmount(item.getMrp());
                    orderItem.setQty(OrderItemdata.getQnty());
                    orderItem.setCreatedDate(new Date());
                    orderItem.setUpdatedDate(new Date());
                    orderItem.setStatus(Orderitem.Status.CONFIRMED.value);
                    orderItemRepository.save(orderItem);

                }
                switch (form.getPaymentMethord()) {

                    case 1:

//                        Stripe.apiKey = "sk_test_51LNZc6SEzov9zFiKT0bkB1bIzsuKGRRJJAhXhx3mrul7gO3A9GDeiIWQspL2JFGaYxfhj5lcURGuMTRlU4lfkLk300lrFQrnBt";
                        Stripe.apiKey = stripeApiKey;
                         {
                            try {

                                CustomerCreateParams params
                                        = CustomerCreateParams.builder()
                                                .setName(user.getName())
                                                .setEmail(user.getEmail())
                                                .setAddress(
                                                        CustomerCreateParams.Address.builder()
                                                                .setLine1(address.getAddress())
                                                                .setPostalCode(address.getZip())
                                                                .setState(address.getState())
                                                                .setCity(address.getCity())
                                                                .build())
                                                .setSource(form.getPaymentToken())
                                                .build();

                                Customer customer = Customer.create(params);
//                                Customer customer = createCustomer(user, address, form.getPaymentToken());
                                System.out.println("==============>" + customer);

                                String sourceCard = Customer.retrieve(customer.getId()).getDefaultSource();
                                List<Object> paymentMethodTypes
                                        = new ArrayList<>();
                                paymentMethodTypes.add("card");
                                Map<String, Object> params1 = new HashMap<>();
                                params1.put("amount", (long) amount);
                                params1.put("description", "purchase");
                                params1.put("currency", "inr");
                                params1.put("confirm", "true");
                                params1.put("payment_method_types", paymentMethodTypes);
                                params1.put("payment_method", sourceCard);
                                params1.put("customer", customer.getId());
                                params1.put("receipt_email", user.getEmail());
                                params1.put("save_payment_method", "true");
                                PaymentIntent paymentIntent
                                        = PaymentIntent.create(params1);

//                                PaymentIntent paymentIntent = createPaymentIntent(sourceCard, customer, user, amount);
                                System.out.println("============>" + paymentIntent);
                                payment.setStatus(Payment.Status.SUCCESS.value);
                                payment.setUpdatedDate(new Date());
                                paymentRepository.save(payment);
                                log.info("Payment successfully completed");

                            } catch (CardException e) {
                                // Since it's a decline, CardException will be caught
                                System.out.println("Status is: " + e.getCode());
                                System.out.println("Message is: " + e.getMessage());
                                log.error(e.getMessage());
                                flag = false;

                            } catch (RateLimitException e) {

                                System.out.println("Status is: " + e.getCode());
                                System.out.println("Message is: " + e.getMessage());
                                log.error(e.getMessage());
                                flag = false;
                                // Too many requests made to the API too quickly
                            } catch (InvalidRequestException e) {
                                System.out.println("Status is: " + e.getCode());
                                System.out.println("Message is: " + e.getMessage());
                                log.error(e.getMessage());
                                flag = false;
                                // Invalid parameters were supplied to Stripe's API
                            } catch (AuthenticationException e) {
                                // Authentication with Stripe's API failed
                                // (maybe you changed API keys recently)s
                                System.out.println("Status is: " + e.getCode());
                                System.out.println("Message is: " + e.getMessage());
                                log.error(e.getMessage());
                                flag = false;

                            } catch (StripeException e) {
                                System.out.println("Status is: " + e.getCode());
                                System.out.println("Message is: " + e.getMessage());
                                log.error(e.getMessage());
                                flag = false;
                                // Display a very generic error to the user, and maybe send
                                // yourself an email
                            } catch (Exception e) {
                                System.out.println("Message is: " + e.getMessage());
                                flag = false;
                                log.error(e.getMessage());

                                // Something else happened, completely unrelated to Stripe
                            }
                        }
                        if (flag == false) {
                            payment.setStatus(Payment.Status.FAILED.value);
                            payment.setUpdatedDate(new Date());
                            paymentRepository.save(payment);
                            order.setStatus(Orders.Status.PAYMENTFAILED.value);
                            order.setUpdatedDate(new Date());
                            ordersRepository.save(order);
                            track.setOrders(order);
                            track.setStatus(Track.Status.PAYMENTFAILED.value);
                            track.setCreatedDate(new Date());
                            track.setUpdatedDate(new Date());
                            trackRepository.save(track);
                            log.info("Failed status updated");
                            v.setErrorCode("1013");
                            v.setErrorMessage("Payment failed");
                        }

                        //
                        break;

                    case 2:
                        payment.setStatus(Payment.Status.CASHONDEVLIVARY.value);
                        payment.setUpdatedDate(new Date());
                        paymentRepository.save(payment);

                        order.setStatus(Orders.Status.PENDING.value);
                        order.setUpdatedDate(new Date());
                        ordersRepository.save(order);
                        break;
                    case 3:
                        //
                        break;
                    case 4:
                    //
                    default:
                        //
                        break;

                }
            }

            if (flag) {
                track.setOrders(order);
                track.setStatus(Track.Status.PENDING.value);
                track.setCreatedDate(new Date());
                track.setUpdatedDate(new Date());
                trackRepository.save(track);
                List<Cart> cartList = cartRepository.findByUserUserIdAndItemIdIn(SecurityUtil.getCurrentUserId(), itemIds);
                for (Cart cart : cartList) {
                    cartRepository.delete(cart);
                }
                v.setErrorCode(null);
                v.setErrorMessage(null);
            }

        }
//        try{
//            mailServiceUtil.orderConfirmation(user.getEmail(), amount);
//        }catch(MessagingException | IOException e){
//            log.error("failed to send mail"+e);
//        }
        return v;
    }

//    @Override
//    public List<ItemOrderDetailsView> getAllOrder() {       
//        List<Orderitem> list=orderItemRepository.getAllItemByUserId(SecurityUtil.getCurrentUserId());
//        for (Orderitem orderitem : list) {
//            System.out.println("orderitem.getAmount()================>"+orderitem.getAmount());
//        }
//        
//        return orderItemRepository.getAllItemByUserId(SecurityUtil.getCurrentUserId())
//                .stream().map(x->new ItemOrderDetailsView(x)).collect(Collectors.toList());
//        
//    }
    @Override
    public Pager<ItemOrderDetailsView> getAllOrder(Integer lmt, String sort,
            boolean type, Integer pge,
            Byte[] orderStatus, String[] orderTime,
            String lastId, String search
    ) {

        Pager<ItemOrderDetailsView> orderPager;
        List<ItemOrderDetailsView> orderlist;
        Predicate datepredicate = null;

        if (pge < 1) {
            throw new BadRequestException(languageUtil.getTranslatedText("page.count.invalid", null, "en"));
        }
        if (lmt < 1) {
            throw new BadRequestException(languageUtil.getTranslatedText("limit.count.invalid", null, "en"));
        }

        Long count;
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Orderitem> criteriaQuery = criteriaBuilder.createQuery(Orderitem.class);
        Root<Orderitem> orderroot = criteriaQuery.from(Orderitem.class);
        Join<Orderitem, Orders> orders = orderroot.join(Orderitem_.ORDERS, JoinType.INNER);
        orders.on(criteriaBuilder.equal(orders.get(Orders_.USER), SecurityUtil.getCurrentUserId()));
        List<Predicate> predicate = new ArrayList<>();

        if (ArrayUtils.isNotEmpty(orderStatus)) {
            Predicate predicateSearch = criteriaBuilder.or(orderroot.get("Shipping").get("status").in(Arrays.asList(orderStatus)));
            predicate.add(predicateSearch);

        }

        if (ArrayUtils.isNotEmpty(orderTime)) {

            for (String s : orderTime) {
                var startDate = s + "-01-31";
                var endDate = s + "-12-31";
                Date inputStartDate = DatetoTimeStampUtil.convertStringToDate(startDate);
                Date inputEndDate = DatetoTimeStampUtil.convertStringToDate(endDate);
                Predicate predicateFrom = criteriaBuilder.between(orderroot.get("createdDate"), inputStartDate, inputEndDate);
                if (datepredicate == null) {
                    datepredicate = predicateFrom;
                } else {
                    datepredicate = criteriaBuilder.or(datepredicate, predicateFrom);
                }

            }
            predicate.add(datepredicate);

        }

        if (lastId != null) {

            Predicate predicateLastid = criteriaBuilder.lessThan(orderroot.get("orderitemId"), Integer.parseInt(lastId));
            predicate.add(predicateLastid);
        }

        if (!StringUtils.isEmpty(search)) {
            Predicate predicateSearch = criteriaBuilder.like(orderroot.get("item").get("name"), "%" + search + "%");
            predicate.add(predicateSearch);

        }

        Predicate And = criteriaBuilder.and(predicate.toArray(Predicate[]::new));
        criteriaQuery.where(And);

        if (sort != null) {
            if (orderFieldExists(sort)) {
                if (type) {
                    criteriaQuery.orderBy(criteriaBuilder.asc(orderroot.get(sort)));
                } else {
                    criteriaQuery.orderBy(criteriaBuilder.desc(orderroot.get(sort)));
                }

            } else {
                throw new BadRequestException(languageUtil.getTranslatedText("item.invalid.sort", null, "en"));
            }
        }

        TypedQuery<Orderitem> list = em.createQuery(criteriaQuery);
        list.setFirstResult((pge - 1) * lmt);
        list.setMaxResults(lmt);
        CriteriaQuery<Long> cqCount = criteriaBuilder.createQuery(Long.class);
        Root<Orderitem> itemcntqury = cqCount.from(criteriaQuery.getResultType());
        cqCount.where(And);
        cqCount.select(criteriaBuilder.count(itemcntqury));
        count = em.createQuery(cqCount).getSingleResult();

        Page<Orderitem> result = new PageImpl<>(list.getResultList(),
                PageRequest.of(pge - 1, lmt), count);

        orderlist = StreamSupport
                .stream(result.spliterator(), false)
                .map(item -> new ItemOrderDetailsView(item)).collect(Collectors.toList());
        orderPager = new Pager<>(lmt, count.intValue(), pge);
        orderPager.setResult(orderlist);

        return orderPager;

    }

    @Override
    public List<OrderView> getOrders() {
        return StreamSupport.stream(ordersRepository.findAllByUserUserIdOrderByCreatedDateDesc(SecurityUtil.getCurrentUserId()).spliterator(), false)
                .map((order) -> {
                    List<Orderitem> orderitem = orderItemRepository.findImage(order.getOrdersId());
                    return new OrderView(order, orderitem);
                })
                .collect(Collectors.toList());

    }

    @Override
    public OrderDetailView getOrderById(Integer orders_id) {
        Orders order = ordersRepository.findByOrdersId(orders_id)
                .orElseThrow(() -> new BadRequestException(languageUtil.getTranslatedText("order.id.notfound", null, "en")));
        List<Orderitem> orderItems = orderItemRepository.findAllByOrdersOrdersIdAndStatus(orders_id, Orderitem.Status.CONFIRMED.value);
        TrackView track2 = new TrackView();
        List<Track> track1 = trackRepository.findAllByOrdersOrdersId(order.getOrdersId());
        track1.stream().forEach((track) -> {

            switch (track.getStatus()) {
                case 0:
                    track2.setCancelledDate(track.getUpdatedDate());
                    break;
                case 1:
                    track2.setRejectedDate(track.getUpdatedDate());
                    break;
                case 2:
                    track2.setPendingDate(track.getUpdatedDate());
                    break;
                case 3:
                    track2.setAcceptedDate(track.getUpdatedDate());
                    break;
                case 4:
                    track2.setOutDate(track.getUpdatedDate());
                    break;
                case 5:
                    track2.setDeliveredDate(track.getUpdatedDate());
                    break;
                case 6:
                    track2.setPaymentFailedDate(track.getUpdatedDate());
                    break;
                case 7:
                    track2.setReturnDate(track.getUpdatedDate());

            }

        });

        return new OrderDetailView(orderItems, order, track2);
    }

    @Override
    public void cancelOrder(Integer orders_id) {
        Orders order = ordersRepository.findByOrdersIdAndStatusNot(orders_id, Orders.Status.DELETED.value)
                .orElseThrow(() -> new BadRequestException(languageUtil.getTranslatedText("order.id.notfound", null, "en")));
        order.setStatus(Orders.Status.DELETED.value);
        order.setUpdatedDate(new Date());
        ordersRepository.save(order);
        Track track = new Track();
        track.setOrders(order);
        track.setStatus(Track.Status.DELETED.value);
        track.setCreatedDate(new Date());
        track.setUpdatedDate(new Date());
        trackRepository.save(track);
    }

    @Override
    public void returnOrder(Integer orders_id) {
        Orders order = ordersRepository.findByOrdersIdAndStatus(orders_id, Orders.Status.DELIVERED.value)
                .orElseThrow(() -> new BadRequestException(languageUtil.getTranslatedText("order.return.notfound", null, "en")));
        order.setStatus(Orders.Status.RETURN.value);
        order.setUpdatedDate(new Date());
        ordersRepository.save(order);
        Track track = new Track();
        track.setOrders(order);
        track.setStatus(Track.Status.RETURN.value);
        track.setCreatedDate(new Date());
        track.setUpdatedDate(new Date());
        trackRepository.save(track);
    }

    public static enum orderSortfields {
        CREATED_DATE("createdDate"), UPDATED_DATE("updatedDate");
        public String value;

        private orderSortfields(String value) {
            this.value = value;
        }
    }

    public static boolean orderFieldExists(String sort) {
        for (OrderServiceImpl.orderSortfields c : OrderServiceImpl.orderSortfields.values()) {
            if (c.value.equals(sort)) {
                return true;
            }
        }
        return false;
    }

//    public Customer createCustomer(User user, Address address, String token) throws StripeException {
//        Stripe.apiKey = stripeApiKey;
//        CustomerCreateParams params
//                = CustomerCreateParams.builder()
//                        .setName(user.getName())
//                        .setEmail(user.getEmail())
//                        .setAddress(
//                                CustomerCreateParams.Address.builder()
//                                        .setLine1(address.getAddress())
//                                        .setPostalCode(address.getZip())
//                                        .setState(address.getState())
//                                        .setCity(address.getCity())
//                                        .build())
//                        .setSource(token)
//                        .build();
//
//        Customer customer = Customer.create(params);
//        return customer;
//
//    }
//
//    public PaymentIntent createPaymentIntent(String sourceCard, Customer customer, User user, float amount) throws StripeException {
//        Stripe.apiKey = stripeApiKey;
//        List<Object> paymentMethodTypes
//                = new ArrayList<>();
//        paymentMethodTypes.add("card");
//        Map<String, Object> params1 = new HashMap<>();
//        params1.put("amount", (long) amount);
//        params1.put("description", "purchase");
//        params1.put("currency", "inr");
//        params1.put("confirm", "true");
//        params1.put("payment_method_types", paymentMethodTypes);
//        params1.put("payment_method", sourceCard);
//        params1.put("customer", customer.getId());
//        params1.put("receipt_email", user.getEmail());
//        params1.put("save_payment_method", "true");
//        PaymentIntent paymentIntent
//                = PaymentIntent.create(params1);
//        return paymentIntent;
//
//    }
}
