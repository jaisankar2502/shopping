/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.service.impl;

import com.shopping.entity.Banner;
import com.shopping.exception.BadRequestException;
import com.shopping.form.BannerForm;
import com.shopping.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shopping.repository.BannerRepository;
import com.shopping.util.LanguageUtil;
import com.shopping.view.BannerView;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *
 * @author ajmal
 */
@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private LanguageUtil languageUtil;

    @Override
    public void addBanner(BannerForm form) {
        bannerRepository.save(new Banner(form));
    }

    @Override
    public List<BannerView> listAllBanner(Byte type) {
        if (type == null) {
            return StreamSupport.stream(bannerRepository.findAllByStatus(Banner.Status.ACTIVE.value)
                    .spliterator(), false).map(banner -> new BannerView(banner)).collect(Collectors.toList());

        } else {
            return StreamSupport.stream(bannerRepository.findAllByStatusAndType(Banner.Status.ACTIVE.value, type)
                    .spliterator(), false).map(banner -> new BannerView(banner)).collect(Collectors.toList());
        }

    }

    @Override
    public BannerView getBannerById(Integer bannerId) {

        return new BannerView(bannerRepository.findByBannerIdAndStatus(bannerId, Banner.Status.ACTIVE.value)
                .orElseThrow(() -> new BadRequestException(languageUtil.getTranslatedText("banner.id.notfound", null, "en"))));
    }

    @Override
    public void updateBanner(Integer bannerId, BannerForm form) {
        Banner banner = bannerRepository.findByBannerIdAndStatus(bannerId, Banner.Status.ACTIVE.value)
                .orElseThrow(() -> new BadRequestException(languageUtil.getTranslatedText("banner.id.notfound", null, "en")));
        banner.setDescription(form.getDescription());
        banner.setImg(form.getImg());
        Byte type = null;
        switch (form.getType()) {
            case 1:
                type = Banner.Type.OFFER.value;
                break;
            case 2:
                type = Banner.Type.SELL.value;
                break;
            case 3:
                type = Banner.Type.NEW.value;
                break;
            default:
                throw new BadRequestException("Invalid Banner Type");
        }
        banner.setType(type);
        banner.setUpdatedDate(new Date());
        bannerRepository.save(banner);

    }

    @Override
    public void deleteBanner(Integer bannerId) {
        Banner banner = bannerRepository.findByBannerIdAndStatus(bannerId, Banner.Status.ACTIVE.value)
                .orElseThrow(() -> new BadRequestException(languageUtil.getTranslatedText("banner.id.notfound", null, "en")));

        banner.setStatus(Banner.Status.DELETED.value);
        banner.setUpdatedDate(new Date());
        bannerRepository.save(banner);
    }

}
