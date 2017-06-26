package com.leway.taipei_hejiang.menu.tea_milk;

import android.content.Context;

import com.leway.taipei_hejiang.R;
import com.leway.taipei_hejiang.model.Drink;

/**
 * Created by Li Chia-en on 2017/5/11.
 */

public class MilkTaroIce extends Drink {

    public MilkTaroIce(Context context) {
        setPrice(60);
        setCount(1);
        setName(context.getString(R.string.milk_melon));
        setCupSize(context.getString(R.string.cup_size_m));
        setDrink(true);
        setSweetFixed(true);
        setOnlyCold(true);
        setImageName("leway");
    }

}
