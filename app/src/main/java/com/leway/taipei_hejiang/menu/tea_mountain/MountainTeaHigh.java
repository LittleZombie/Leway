package com.leway.taipei_hejiang.menu.tea_mountain;

import android.content.Context;

import com.leway.taipei_hejiang.R;
import com.leway.taipei_hejiang.model.Drink;

/**
 * Created by Li Chia-en on 2017/5/11.
 */

public class MountainTeaHigh extends Drink {

    public MountainTeaHigh(Context context) {
        setPrice(65);
        setCount(1);
        setName(context.getString(R.string.mountain_tea_high));
        setCupSize(context.getString(R.string.cup_size_900));
        setDrink(true);
        setSweetFixed(false);
        setImageName("leway");
    }

}
