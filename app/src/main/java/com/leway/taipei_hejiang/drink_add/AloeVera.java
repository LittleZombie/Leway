package com.leway.taipei_hejiang.drink_add;

import android.content.Context;

import com.leway.taipei_hejiang.R;
import com.leway.taipei_hejiang.model.Product;

/**
 * Created by Li Chia-en on 2017/5/9.
 */

public class AloeVera extends Product {

    public AloeVera(Context context) {
        setDrink(false);
        setName(context.getString(R.string.add_aloe_vera));
        setCount(1);
        setPrice(10);
    }

}
