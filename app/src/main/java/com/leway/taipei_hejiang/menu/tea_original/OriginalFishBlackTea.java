package com.leway.taipei_hejiang.menu.tea_original;

import android.content.Context;

import com.leway.taipei_hejiang.R;
import com.leway.taipei_hejiang.model.Drink;

/**
 * Created by Li Chia-en on 2017/5/11.
 */

public class OriginalFishBlackTea extends Drink {

    public OriginalFishBlackTea(Context context) {
        setPrice(30);
        setCount(1);
        setName(context.getString(R.string.original_fish_black_tea));
        setCupSize(context.getString(R.string.cup_size_l));
        setDrink(true);
        setSweetFixed(false);
        setImageName("leway");
    }

}
