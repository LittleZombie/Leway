package com.leway.taipei_hejiang.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.leway.taipei_hejiang.model.Drink;
import com.leway.taipei_hejiang.model.Product;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Li Chia-en on 2017/5/15.
 */

public class SaveManager {

    private static String TAG = "SaveManager";

    private static final String FILE_NAME = "LEWAY";
    private static final String SAVE_PRODUCTS = "SAVE_PRODUCTS";

    public static void saveProduct(Context context, Product product) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE);

        List<Product> productList = revertProducts(context);
        if (productList == null || productList.size() <= 0) {
            productList = new ArrayList<>();
            productList.add(product);
            String productString = new Gson().toJson(productList);
            sharedPreferences.edit().putString(SAVE_PRODUCTS, productString).apply();
            return;
        }

        boolean hasSameProduct = false;
        for (Product saveProduct : productList) {
            if (product.getName().equals(saveProduct.getName())) {
                if (product.isDrink() && saveProduct.isDrink()) {

                    Drink saveDrink = (Drink) saveProduct;
                    Drink addDrink = (Drink) product;
                    if (TextUtils.isEmpty(addDrink.getHotOrCold())) {
                        // Same Food
                        int addProductCount = product.getCount();
                        int originalCount = saveProduct.getCount();
                        int total = addProductCount + originalCount;
                        saveProduct.setCount(total);
                        String productString = new Gson().toJson(productList);
                        sharedPreferences.edit().putString(SAVE_PRODUCTS, productString).apply();
                        hasSameProduct = true;
                    } else {

                        if (addDrink.isSweetFixed()) {
                            if (saveDrink.getHotOrCold().equals(addDrink.getHotOrCold())) {
                                int addProductCount = addDrink.getCount();
                                int originalCount = saveProduct.getCount();
                                int total = addProductCount + originalCount;
                                saveProduct.setCount(total);
                                String productString = new Gson().toJson(productList);
                                sharedPreferences.edit().putString(SAVE_PRODUCTS, productString).apply();
                                hasSameProduct = true;
                            }
                        } else {
                            if (saveDrink.getHotOrCold().equals(addDrink.getHotOrCold())
                                    && saveDrink.getSweet().equals(addDrink.getSweet())) {
                                if (saveDrink.getAddProduct() != null && addDrink.getAddProduct() != null
                                        && saveDrink.getAddProduct().getName().equals(addDrink.getAddProduct().getName())) {
                                    // Same Drink with temperature, sweet and add.
                                    int addProductCount = addDrink.getCount();
                                    int originalCount = saveProduct.getCount();
                                    int total = addProductCount + originalCount;
                                    saveProduct.setCount(total);
                                    String productString = new Gson().toJson(productList);
                                    sharedPreferences.edit().putString(SAVE_PRODUCTS, productString).apply();
                                    hasSameProduct = true;
                                }
                            }
                        }
                    }

                } else if (!product.isDrink() && !saveProduct.isDrink()) {
                    // Same Food
                    int addProductCount = product.getCount();
                    int originalCount = saveProduct.getCount();
                    int total = addProductCount + originalCount;
                    saveProduct.setCount(total);
                    String productString = new Gson().toJson(productList);
                    sharedPreferences.edit().putString(SAVE_PRODUCTS, productString).apply();
                    hasSameProduct = true;
                }
            }
        }

        if (!hasSameProduct) {
            productList.add(product);
            String productString = new Gson().toJson(productList);
            sharedPreferences.edit().putString(SAVE_PRODUCTS, productString).apply();
        }
    }

    public static List<Product> revertProducts(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        String productString = sharedPreferences.getString(SAVE_PRODUCTS, "");
        Log.e(TAG, "get : " + productString);

        Type type = new TypeToken<List<Drink>>() {
        }.getType();
        return new Gson().fromJson(productString, type);
    }

    public static void clearAllProducts(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        sharedPreferences.edit().putString(SAVE_PRODUCTS, "").apply();
    }

    public static void saveProductList(Context context, List<Product> products) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        String productString = new Gson().toJson(products);
        sharedPreferences.edit().putString(SAVE_PRODUCTS, productString).apply();
    }

}
