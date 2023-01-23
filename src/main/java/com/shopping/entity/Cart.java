    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.entity;

import com.shopping.form.CartForm;
import com.shopping.form.CartItems;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author albinps
 */
@Entity
public class Cart {

   
     public static enum Status {
        DELETED((byte) 0),
        ACTIVE((byte) 1),
        CART((byte) 2),
        SAVEFORLATER((byte) 3);

        public final byte value;

        private Status(byte value   ) {
            this.value = value;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;
    private Integer qty;
    private byte status;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    
    
    public Cart() {
    }
    
     public Cart(CartForm form,Integer userId) {
        this.qty=form.getQty();
        this.user=new User(userId);
        this.item=new Item(form.getItemId());
        this.status=form.getIsCart() ? Cart.Status.CART.value : Cart.Status.SAVEFORLATER.value; 
        Date dt = new Date();
        this.createDate = dt;
        this.updateDate = dt;
    }
     
     public Cart(CartItems form,Integer userId,Boolean isCart) {
        this.qty=form.getQty();
        this.user=new User(userId);
        this.item=new Item(form.getItemId());
        this.status=isCart ? Cart.Status.CART.value : Cart.Status.SAVEFORLATER.value; 
        Date dt = new Date();
        this.createDate = dt;
        this.updateDate = dt;
    }
    
    public Integer getCartId() {
        return cartId;
    }

    

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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
    
}
