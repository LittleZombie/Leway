package com.leway.taipei_hejiang.menu.tea_original;

import android.content.Context;

import com.leway.taipei_hejiang.R;
import com.leway.taipei_hejiang.model.Drink;

/**
 * Created by Li Chia-en on 2017/5/11.
 */

public class OriginalSweetTea extends Drink {

    public OriginalSweetTea(Context context) {
        setPrice(35);
        setCount(1);
        setName(context.getString(R.string.original_sweet_tea));
        setCupSize(context.getString(R.string.cup_size_l));
        setDrink(true);
        setSweetFixed(true);
        setImageName("leway");
    }

}
