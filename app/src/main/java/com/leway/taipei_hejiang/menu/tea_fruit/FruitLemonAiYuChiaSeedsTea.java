package com.leway.taipei_hejiang.menu.tea_fruit;

import android.content.Context;

import com.leway.taipei_hejiang.R;
import com.leway.taipei_hejiang.model.Drink;

/**
 * Created by Li Chia-en on 2017/7/23.
 */

public class FruitLemonAiYuChiaSeedsTea extends Drink {

    public FruitLemonAiYuChiaSeedsTea(Context context) {
        setPrice(60);
        setCount(1);
        setName(context.getString(R.string.fruit_lemon_aiyu_chia_tea));
        setCupSize(context.getString(R.string.cup_size_l));
        setDrink(true);
        setSweetFixed(false);
        setOnlyCold(true);
        setImageName("leway");
    }

}
