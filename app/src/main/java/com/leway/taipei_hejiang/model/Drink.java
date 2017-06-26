package com.leway.taipei_hejiang.model;

import java.io.Serializable;

/**
 * Created by Li Chia-en on 2017/5/9.
 */

public class Drink extends Product implements Serializable {

    private String sweet;

    private String hotOrCold;

    private String cupSize;

    private boolean isOnlyCold;

    private boolean isSweetFixed;

    private Product addProduct;


    public String getSweet() {
        return sweet;
    }

    public void setSweet(String sweet) {
        this.sweet = sweet;
    }

    public String getHotOrCold() {
        return hotOrCold;
    }

    public void setHotOrCold(String hotOrCold) {
        this.hotOrCold = hotOrCold;
    }

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public boolean isOnlyCold() {
        return isOnlyCold;
    }

    public void setOnlyCold(boolean onlyCold) {
        isOnlyCold = onlyCold;
    }

    public boolean isSweetFixed() {
        return isSweetFixed;
    }

    public void setSweetFixed(boolean sweetFixed) {
        isSweetFixed = sweetFixed;
    }

    public Product getAddProduct() {
        return addProduct;
    }

    public void setAddProduct(Product addProduct) {
        this.addProduct = addProduct;
    }
}
