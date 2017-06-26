package com.leway.taipei_hejiang.menu.tea_milk;

import android.content.Context;

import com.leway.taipei_hejiang.R;
import com.leway.taipei_hejiang.model.Drink;

/**
 * Created by Li Chia-en on 2017/5/11.
 */

public class MilkTaroTapioca extends Drink {

    public MilkTaroTapioca(Context context) {
        setPrice(65);
        setCount(1);
        setName(context.getString(R.string.milk_taro_tapioca));
        setCupSize(context.getString(R.string.cup_size_m));
        setDrink(true);
        setSweetFixed(true);
        setImageName("leway");
    }

}
