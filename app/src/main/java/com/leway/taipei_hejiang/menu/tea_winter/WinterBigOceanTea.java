package com.leway.taipei_hejiang.menu.tea_winter;

import android.content.Context;

import com.leway.taipei_hejiang.R;
import com.leway.taipei_hejiang.model.Drink;

/**
 * Created by Li Chia-en on 2017/5/11.
 */

public class WinterBigOceanTea extends Drink {

    public WinterBigOceanTea(Context context) {
        setPrice(40);
        setCount(1);
        setName(context.getString(R.string.winter_big_ocean_tea));
        setCupSize(context.getString(R.string.cup_size_m));
        setDrink(true);
        setSweetFixed(true);
        setImageName("leway");
    }

}
