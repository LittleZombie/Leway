package com.leway.taipei_hejiang.drink_add;

import android.content.Context;

import com.leway.taipei_hejiang.R;
import com.leway.taipei_hejiang.model.Product;

/**
 * Created by Li Chia-en on 2017/5/9.
 */

public class SagoDew extends Product {

    public SagoDew(Context context) {
        setDrink(false);
        setName(context.getString(R.string.add_sago_dew));
        setCount(1);
        setPrice(5);
    }

}
