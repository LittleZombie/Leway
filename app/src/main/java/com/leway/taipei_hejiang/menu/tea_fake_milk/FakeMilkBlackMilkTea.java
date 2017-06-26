package com.leway.taipei_hejiang.menu.tea_fake_milk;

import android.content.Context;

import com.leway.taipei_hejiang.R;
import com.leway.taipei_hejiang.model.Drink;

/**
 * Created by Li Chia-en on 2017/5/11.
 */

public class FakeMilkBlackMilkTea extends Drink {

    public FakeMilkBlackMilkTea(Context context) {
        setPrice(40);
        setCount(1);
        setName(context.getString(R.string.fake_milk_black_milk_tea));
        setCupSize(context.getString(R.string.cup_size_l));
        setDrink(true);
        setSweetFixed(false);
        setImageName("leway");
    }

}
