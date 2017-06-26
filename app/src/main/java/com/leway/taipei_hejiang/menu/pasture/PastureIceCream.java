package com.leway.taipei_hejiang.menu.pasture;

import android.content.Context;

import com.leway.taipei_hejiang.R;
import com.leway.taipei_hejiang.model.Drink;

/**
 * Created by Li Chia-en on 2017/5/11.
 */

public class PastureIceCream extends Drink {

    public PastureIceCream(Context context) {
        setPrice(40);
        setCount(1);
        setName(context.getString(R.string.pasture_ice_cream));
        setDrink(false);
        setImageName("leway");
    }

}
