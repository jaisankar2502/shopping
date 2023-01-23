/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.service.impl;

import com.shopping.entity.Orders;
import com.shopping.entity.Track;
import com.shopping.exception.BadRequestException;
import com.shopping.repository.OrdersRepository;
import com.shopping.repository.TrackRepository;
import com.shopping.service.TrackService;
import com.shopping.util.LanguageUtil;
import com.shopping.view.OrderTrackView;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ajmal
 */
@Service
public class TrackServiceImpl implements TrackService {

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private LanguageUtil languageUtil;

    @Override
    public void Accept(Integer orderId) {

        Orders orders = ordersRepository.findByOrdersId(orderId)
                .orElseThrow(() -> new BadRequestException(languageUtil.getTranslatedText("order.id.notfound", null, "en")));
        if (orders.getStatus() == Orders.Status.PAYMENTFAILED.value) {
            throw new BadRequestException(languageUtil.getTranslatedText("transaction.failed", null, "en"));
        }
        if (orders.getStatus() == Orders.Status.DELETED.value) {
            throw new BadRequestException(languageUtil.getTranslatedText("order.deleted", null, "en"));
        }
        if (orders.getStatus() == Orders.Status.REJECTED.value) {
            throw new BadRequestException(languageUtil.getTranslatedText("order.rejected", null, "en"));
        }
        if (orders.getStatus() == Orders.Status.PENDING.value) {
            orders.setStatus(Orders.Status.ACCEPTED.value);
            orders.setUpdatedDate(new Date());
            ordersRepository.save(orders);
            Track track = new Track();
            track.setOrders(orders);
            track.setStatus(Track.Status.ACCEPTED.value);
            track.setCreatedDate(new Date());
            track.setUpdatedDate(new Date());
            trackRepository.save(track);

        } else {
            throw new BadRequestException(languageUtil.getTranslatedText("order.status", null, "en"));
        }

    }

    @Override
    public void outForDelivery(Integer orderId) {
        Orders orders = ordersRepository.findByOrdersId(orderId)
                .orElseThrow(() -> new BadRequestException(languageUtil.getTranslatedText("order.id.notfound", null, "en")));
        if (orders.getStatus() == Orders.Status.PAYMENTFAILED.value) {
            throw new BadRequestException(languageUtil.getTranslatedText("transaction.failed", null, "en"));
        }
        if (orders.getStatus() == Orders.Status.DELETED.value) {
            throw new BadRequestException(languageUtil.getTranslatedText("order.deleted", null, "en"));
        }
        if (orders.getStatus() == Orders.Status.REJECTED.value) {
            throw new BadRequestException(languageUtil.getTranslatedText("order.rejected", null, "en"));
        }
        if (orders.getStatus() == Orders.Status.PENDING.value) {
            throw new BadRequestException(languageUtil.getTranslatedText("accept.order", null, "en"));
        }

        if (orders.getStatus() == Orders.Status.ACCEPTED.value) {
            orders.setStatus(Orders.Status.OUTFORDELIVERY.value);
            orders.setUpdatedDate(new Date());
            ordersRepository.save(orders);
            Track track = new Track();
            track.setOrders(orders);
            track.setStatus(Track.Status.OUTFORDELIVERY.value);
            track.setCreatedDate(new Date());
            track.setUpdatedDate(new Date());
            trackRepository.save(track);

        } else {
            throw new BadRequestException(languageUtil.getTranslatedText("order.status", null, "en"));
        }

    }

    @Override
    public void delivered(Integer orderId) {
        Orders orders = ordersRepository.findByOrdersId(orderId)
                .orElseThrow(() -> new BadRequestException(languageUtil.getTranslatedText("order.id.notfound", null, "en")));
        if (orders.getStatus() == Orders.Status.PAYMENTFAILED.value) {
            throw new BadRequestException(languageUtil.getTranslatedText("transaction.failed", null, "en"));
        }
        if (orders.getStatus() == Orders.Status.DELETED.value) {
            throw new BadRequestException(languageUtil.getTranslatedText("order.deleted", null, "en"));
        }
        if (orders.getStatus() == Orders.Status.REJECTED.value) {
            throw new BadRequestException(languageUtil.getTranslatedText("order.rejected", null, "en"));
        }
        if (orders.getStatus() == Orders.Status.ACCEPTED.value) {
            throw new BadRequestException(languageUtil.getTranslatedText("accept.order", null, "en"));
        }
        if (orders.getStatus() == Orders.Status.OUTFORDELIVERY.value) {
            orders.setStatus(Orders.Status.DELIVERED.value);
            orders.setUpdatedDate(new Date());
            ordersRepository.save(orders);
            Track track = new Track();
            track.setOrders(orders);
            track.setStatus(Track.Status.DELIVERED.value);
            track.setCreatedDate(new Date());
            track.setUpdatedDate(new Date());
            trackRepository.save(track);

        } else {
            throw new BadRequestException(languageUtil.getTranslatedText("order.status", null, "en"));
        }

    }

    @Override
    public void reject(Integer orderId) {
        Orders orders = ordersRepository.findByOrdersId(orderId)
                .orElseThrow(() -> new BadRequestException(languageUtil.getTranslatedText("order.id.notfound", null, "en")));
        if (orders.getStatus() == Orders.Status.DELETED.value) {
            throw new BadRequestException(languageUtil.getTranslatedText("order.deleted", null, "en"));
        }
        if (orders.getStatus() != Orders.Status.PENDING.value) {
            throw new BadRequestException(languageUtil.getTranslatedText("order.already.accepted", null, "en"));

        } else {
            orders.setStatus(Orders.Status.REJECTED.value);
            orders.setUpdatedDate(new Date());
            ordersRepository.save(orders);
            Track track = new Track();
            track.setOrders(orders);
            track.setStatus(Track.Status.REJECTED.value);
            track.setCreatedDate(new Date());
            track.setUpdatedDate(new Date());
            trackRepository.save(track);
        }
//        if (orders.getStatus() == Orders.Status.PAYMENTFAILED.value) {
//            throw new BadRequestException(languageUtil.getTranslatedText("transaction.failed", null, "en"));
//        }

    }

    @Override
    public void delete(Integer orderId) {
        Orders orders = ordersRepository.findByOrdersId(orderId)
                .orElseThrow(() -> new BadRequestException(languageUtil.getTranslatedText("order.id.notfound", null, "en")));
        if (orders.getStatus() == Orders.Status.DELETED.value) {
            throw new BadRequestException(languageUtil.getTranslatedText("order.deleted", null, "en"));
        }
    
            orders.setStatus(Orders.Status.DELETED.value);
            orders.setUpdatedDate(new Date());
            ordersRepository.save(orders);
            Track track = new Track();
            track.setOrders(orders);
            track.setStatus(Track.Status.DELETED.value);
            track.setCreatedDate(new Date());
            track.setUpdatedDate(new Date());
            trackRepository.save(track);
        
    }

    @Override
    public List<OrderTrackView> getTrack(Integer orderId) {
        return StreamSupport.stream(trackRepository.
                findAllByOrdersOrdersId(orderId).spliterator(), false)
                .map(track -> new OrderTrackView(track))
                .collect(Collectors.toList());
        
    }

}
