/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.controller;

import com.shopping.form.BannerForm;
import com.shopping.service.BannerService;
import com.shopping.view.BannerView;
import java.util.List;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ajmal
 */
@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @PostMapping
    public void addBanner(@Valid @RequestBody BannerForm form) {
        bannerService.addBanner(form);
    }

    @GetMapping
    public List<BannerView> listAllBanner(@RequestParam(name = "type",required = false) Byte type) {
        return bannerService.listAllBanner(type);
    }

    @GetMapping("/{bannerId}")
    public BannerView getBannerById(@PathVariable(name = "bannerId") Integer bannerId) {
        return bannerService.getBannerById(bannerId);

    }

    @PutMapping("/{bannerId}")
    public void updateBanner(
            @PathVariable(name = "bannerId") Integer bannerId,
            @Valid @RequestBody BannerForm form
    ) {
        bannerService.updateBanner(bannerId, form);
    }

    @DeleteMapping("/{bannerId}")
    public void deleteBanner(@PathVariable(name = "bannerId") Integer bannerId) {
        bannerService.deleteBanner(bannerId);
    }

}
