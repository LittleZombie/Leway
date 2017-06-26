package com.leway.taipei_hejiang.menu.tea_fruit;

import android.content.Context;

import com.leway.taipei_hejiang.R;
import com.leway.taipei_hejiang.model.Drink;

/**
 * Created by Li Chia-en on 2017/5/11.
 */

public class FruitEmeraldLemonGreen extends Drink {

    public FruitEmeraldLemonGreen(Context context) {
        setPrice(50);
        setCount(1);
        setName(context.getString(R.string.fruit_emerald_lemon_green));
        setCupSize(context.getString(R.string.cup_size_l));
        setDrink(true);
        setSweetFixed(false);
        setOnlyCold(true);
        setImageName("leway");
    }

}
