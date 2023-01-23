/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shopping.service;

import com.shopping.form.BannerForm;
import com.shopping.view.BannerView;
import java.util.List;

/**
 *
 * @author ajmal
 */
public interface BannerService {

    public void addBanner(BannerForm form);

    public List<BannerView> listAllBanner(Byte type);

    public BannerView getBannerById(Integer bannerId);

    public void updateBanner(Integer bannerId, BannerForm form);

    public void deleteBanner(Integer bannerId);

}
