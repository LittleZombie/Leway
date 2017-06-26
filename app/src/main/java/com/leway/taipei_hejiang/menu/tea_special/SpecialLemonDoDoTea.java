package com.leway.taipei_hejiang.menu.tea_special;

import android.content.Context;

import com.leway.taipei_hejiang.R;
import com.leway.taipei_hejiang.model.Drink;

/**
 * Created by Li Chia-en on 2017/5/11.
 */

public class SpecialLemonDoDoTea extends Drink {

    public SpecialLemonDoDoTea(Context context) {
        setPrice(55);
        setCount(1);
        setName(context.getString(R.string.special_lemon_dodo_tea));
        setCupSize(context.getString(R.string.cup_size_l));
        setDrink(true);
        setSweetFixed(false);
        setOnlyCold(true);
        setImageName("leway");
    }

}
