package com.leway.taipei_hejiang.model;

import java.io.Serializable;

/**
 * Created by Li Chia-en on 2017/5/9.
 */

public class Product implements Serializable {

    private boolean isDrink; //false is Food

    private String name;

    private String imageName;

    private int price;

    private int count;


    public boolean isDrink() {
        return isDrink;
    }

    public void setDrink(boolean drink) {
        isDrink = drink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
