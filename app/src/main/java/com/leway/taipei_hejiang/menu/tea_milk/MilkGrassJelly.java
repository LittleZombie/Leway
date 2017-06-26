package com.leway.taipei_hejiang.menu.tea_milk;

import android.content.Context;

import com.leway.taipei_hejiang.R;
import com.leway.taipei_hejiang.model.Drink;

/**
 * Created by Li Chia-en on 2017/5/11.
 */

public class MilkGrassJelly extends Drink {

    public MilkGrassJelly(Context context) {
        setPrice(60);
        setCount(1);
        setName(context.getString(R.string.milk_grass_jelly));
        setCupSize(context.getString(R.string.cup_size_m));
        setDrink(true);
        setSweetFixed(false);
        setOnlyCold(true);
        setImageName("leway");
    }

}
