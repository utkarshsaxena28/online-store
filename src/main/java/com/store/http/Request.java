package com.store.http;

import com.store.entity.Product;
import com.store.entity.User;

import javax.validation.Valid;

public class Request {

    private Product prod;

    @Valid
    private User user;
    public Request() {

    }
    public Product getProduct() {
        return prod;
    }

    public void setProduct(Product prod) {
        this.prod = prod;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
