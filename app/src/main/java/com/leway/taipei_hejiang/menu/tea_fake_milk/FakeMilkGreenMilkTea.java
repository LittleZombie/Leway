package com.leway.taipei_hejiang.menu.tea_fake_milk;

import android.content.Context;

import com.leway.taipei_hejiang.R;
import com.leway.taipei_hejiang.model.Drink;

/**
 * Created by Li Chia-en on 2017/5/11.
 */

public class FakeMilkGreenMilkTea extends Drink {

    public FakeMilkGreenMilkTea(Context context) {
        setPrice(40);
        setCount(1);
        setName(context.getString(R.string.fake_milk_green_milk_tea));
        setCupSize(context.getString(R.string.cup_size_l));
        setDrink(true);
        setSweetFixed(false);
        setImageName("leway");
    }

}
