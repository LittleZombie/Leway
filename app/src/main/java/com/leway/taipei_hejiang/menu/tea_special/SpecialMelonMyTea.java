package com.leway.taipei_hejiang.menu.tea_special;

import android.content.Context;

import com.leway.taipei_hejiang.R;
import com.leway.taipei_hejiang.model.Drink;

/**
 * Created by Li Chia-en on 2017/5/11.
 */

public class SpecialMelonMyTea extends Drink {

    public SpecialMelonMyTea(Context context) {
        setPrice(40);
        setCount(1);
        setName(context.getString(R.string.special_melon_my_tea));
        setCupSize(context.getString(R.string.cup_size_l));
        setDrink(true);
        setSweetFixed(true);
        setImageName("leway");
    }

}
