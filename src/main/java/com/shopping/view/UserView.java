/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopping.view;

import com.shopping.entity.User;
import com.shopping.json.Json;
import java.util.Date;

/**
 *
 * @author nirmal
 */
public class UserView {

    private final int userId;
    private final String name;
    private final String email;
    private final short status;
    @Json.DateTimeFormat
    private final Date createDate;
    @Json.DateTimeFormat
    private final Date updateDate;
    @Json.DateFormat
    private final Date dob;
    private final short type;

    public UserView(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.status = user.getStatus();
        this.createDate = user.getCreateDate();
        this.updateDate = user.getUpdateDate();
        this.type=user.getType();
        this.dob=user.getDob();
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public short getStatus() {
        return status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public Date getDob() {
        return dob;
    }

    public short getType() {
        return type;
    }
    
}
