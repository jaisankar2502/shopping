/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopping.entity;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.shopping.form.UserForm;

/**
 *
 * @author nirmal
 */
@Entity
public class User {

    public static enum Status {
        INACTIVE((byte) 0),
        ACTIVE((byte) 1);

        public final byte value;

        private Status(byte value) {
            this.value = value;
        }
    }
    
    public static enum Type {
        GMAIL((byte) 1),
        GOOGLE((byte) 2),
        APPLE((byte) 3);

        public final byte value;

        private Type(byte value) {
            this.value = value;
        }
    }
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String name;
    private String email;
    private String password;
    private byte status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date Dob;
    private byte type;

    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }

    public User(String name, String email, String password,Date date) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type=Type.GMAIL.value;
        this.status = Status.ACTIVE.value;
        this.Dob=date;

        Date dt = new Date();
        this.createDate = dt;
        this.updateDate = dt;
    }  
    public User(String name,Date date) {
        this.name = name;        
        this.Dob=date;
        Date dt = new Date();
        this.updateDate = dt;
    }  
    
    public User(String name, String email, String password, byte type) {
        this.name = name;
        this.email = email;
        this.password = password;

        this.status = Status.ACTIVE.value;
        this.type=type;

        Date dt = new Date();
        this.createDate = dt;
        this.updateDate = dt;
    }

    
    
    public User update(UserForm form) {
        this.name = form.getName();
        this.email = form.getEmail();
        this.password = form.getPassword();
        
        return this;
    }    
    
    public User changePassword(String password) {
        this.password = password;
        this.updateDate = new Date();
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getDob() {
        return Dob;
    }

    public void setDob(Date Dob) {
        this.Dob = Dob;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        return Objects.equals(this.userId, other.userId);
    }

    @Override
    public String toString() {
        return "com.innovaturelabs.training.contacts.entity.User[ userId=" + userId + " ]";
    }
}
