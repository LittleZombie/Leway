package com.leway.taipei_hejiang.utils;

import com.leway.taipei_hejiang.model.Drink;
import com.leway.taipei_hejiang.model.Product;

import java.util.List;

/**
 * Created by Li Chia-en on 2017/5/15.
 */

public class CalculateUtil {

    public static int oneProduct(Product product) {
        if (product.isDrink()) {
            Drink drink = (Drink) product;
            int cup = drink.getCount();
            int originalPrice = drink.getPrice();

            Product add = drink.getAddProduct();
            int addPrice = add == null ? 0 : add.getPrice();

            return (originalPrice + addPrice) * cup;
        } else {
            int count = product.getCount();
            int price = product.getPrice();
            return price * count;
        }
    }

    public static int totalProductPrice(List<Product> productList){
        int total = 0;
        if(productList == null || productList.size() <= 0){
            return total;
        }

        for(Product product : productList){
            total += oneProduct(product);
        }
        return total;
    }

}
