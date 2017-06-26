package com.leway.taipei_hejiang.menu.pasture;

import android.content.Context;

import com.leway.taipei_hejiang.R;
import com.leway.taipei_hejiang.model.Drink;

/**
 * Created by Li Chia-en on 2017/5/11.
 */

public class PastureMilk extends Drink {

    public PastureMilk(Context context) {
        setPrice(95);
        setCount(1);
        setName(context.getString(R.string.pasture_milk));
        setDrink(false);
        setImageName("leway");
    }

}
