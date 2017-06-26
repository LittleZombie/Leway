package com.leway.taipei_hejiang.menu.sweet_food;

import android.content.Context;

import com.leway.taipei_hejiang.R;
import com.leway.taipei_hejiang.model.Drink;
import com.leway.taipei_hejiang.model.Product;

/**
 * Created by Li Chia-en on 2017/5/11.
 */

public class SweetFoodCheeseCake extends Drink {

    public SweetFoodCheeseCake(Context context) {
        setPrice(65);
        setCount(1);
        setName(context.getString(R.string.sweet_food_cheese_cake));
        setDrink(false);
        setImageName("leway");
    }

}
