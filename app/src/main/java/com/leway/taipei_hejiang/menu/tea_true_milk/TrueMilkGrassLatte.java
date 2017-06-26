package com.leway.taipei_hejiang.menu.tea_true_milk;

import android.content.Context;

import com.leway.taipei_hejiang.R;
import com.leway.taipei_hejiang.model.Drink;

/**
 * Created by Li Chia-en on 2017/5/11.
 */

public class TrueMilkGrassLatte extends Drink {

    public TrueMilkGrassLatte(Context context) {
        setPrice(60);
        setCount(1);
        setName(context.getString(R.string.true_milk_grass_latte));
        setCupSize(context.getString(R.string.cup_size_l));
        setDrink(true);
        setSweetFixed(false);
        setImageName("leway");
    }

}
