package com.store.http;

import com.store.entity.Product;

import java.util.List;

public class Response {

    private String message;
    private List<Product> list;

    public Response() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }
}
