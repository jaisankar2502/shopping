/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.view;

import com.shopping.entity.Features;
import com.shopping.json.Json;
import java.util.Date;

/**
 *
 * @author ajmal
 */
public class FeatureView {
    
    private final Integer features_id;
    private final String features_desc;
    private final byte status;
    @Json.DateTimeFormat
    private final Date createDate;
    @Json.DateTimeFormat
    private final Date updateDate;

    public FeatureView(Features feature) {
        this.features_id = feature.getFeaturesId();
        this.features_desc = feature.getFeatures_desc();
        this.status = feature.getStatus();
        this.createDate = feature.getCreatedDate();
        this.updateDate = feature.getUpdatedDate();
    }

    public Integer getFeatures_id() {
        return features_id;
    }

    public String getFeatures_desc() {
        return features_desc;
    }

    public byte getStatus() {
        return status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

}
