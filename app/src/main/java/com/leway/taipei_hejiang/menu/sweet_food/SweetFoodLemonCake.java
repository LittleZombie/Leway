package com.leway.taipei_hejiang.menu.sweet_food;

import android.content.Context;

import com.leway.taipei_hejiang.R;
import com.leway.taipei_hejiang.model.Drink;
import com.leway.taipei_hejiang.model.Product;

/**
 * Created by Li Chia-en on 2017/5/11.
 */

public class SweetFoodLemonCake extends Drink {

    public SweetFoodLemonCake(Context context) {
        setPrice(30);
        setCount(1);
        setName(context.getString(R.string.sweet_food_lemon_cake));
        setDrink(false);
        setImageName("leway");
    }

}
