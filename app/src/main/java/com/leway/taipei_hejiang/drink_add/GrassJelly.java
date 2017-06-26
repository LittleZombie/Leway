package com.leway.taipei_hejiang.drink_add;

import android.content.Context;

import com.leway.taipei_hejiang.R;
import com.leway.taipei_hejiang.model.Product;

/**
 * Created by Li Chia-en on 2017/5/9.
 */

public class GrassJelly extends Product {

    public GrassJelly(Context context) {
        setDrink(false);
        setName(context.getString(R.string.add_grass_jelly));
        setCount(1);
        setPrice(5);
    }

}
