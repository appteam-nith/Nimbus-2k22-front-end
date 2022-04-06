package com.nith.nimbus2k22.Models;

public class ProductImage {
    int id;
    String product;
    String prodimageUrl;

    public ProductImage(int id, String product, String prodimageUrl) {
        this.id = id;
        this.product = product;
        this.prodimageUrl = prodimageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProdimageUrl() {
        return prodimageUrl;
    }

    public void setProdimageUrl(String prodimageUrl) {
        this.prodimageUrl = prodimageUrl;
    }
}
