package com.leway.taipei_hejiang.utils;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.leway.taipei_hejiang.R;
import com.leway.taipei_hejiang.model.CustomerOrderInfo;
import com.leway.taipei_hejiang.model.Drink;
import com.leway.taipei_hejiang.model.Product;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Li Chia-en on 2017/5/21.
 */

public class MailStringUtils {

    public static String createMailBody(Context context, CustomerOrderInfo info){
        String body = "";
        body += "訂購人資訊\n";
        body += context.getString(R.string.company) + ":" + info.getCompany() + "\n";
        body += context.getString(R.string.order_name) + ":" + info.getName() + "\n";
        body += context.getString(R.string.order_phone) + ":" + info.getPhone() + "\n";
        body += context.getString(R.string.take_product_method) + ":" + info.getMethod() + "\n";

        String address = info.getAddress();
        if(!TextUtils.isEmpty(address)) {
            body += context.getString(R.string.take_other_address) + ":" + address + "\n";
        }
        body += context.getString(R.string.date) + ":" + info.getDate() + "\n";
        body += context.getString(R.string.time) + ":" + info.getTime() + "\n";
        body += "------------------------------------------------------\n";

        List<Product> products = SaveManager.revertProducts(context);
        for(Product product : products){
            if(product.isDrink()){
                body += createDrinkString(context, product);
            }else{
                body += "[" + String.valueOf(product.getCount()) + context.getString(R.string.food_size) + "]" + product.getName() + "\n";
            }
        }

        body += "------------------------------------------------------\n";

        int totalPrice = CalculateUtil.totalProductPrice(products);
        body += context.getString(R.string.calculate_total) + String.valueOf(totalPrice) + " (From Android App)";
        return body;
    }

    private static String createDrinkString(Context context, Product product){
        String text = "";
        String string = new Gson().toJson(product);
        Type type = new TypeToken<Drink>() {}.getType();
        Drink drink = new Gson().fromJson(string, type);

        // cup size
        text += "[" + String.valueOf(drink.getCount()) + context.getString(R.string.cup) + "]";

        // name
        text += drink.getName() + "{";

        String sweet = drink.getSweet();
        if(!TextUtils.isEmpty(sweet)){
            text += sweet + ", ";
        }

        text += drink.getHotOrCold();

        Product add = drink.getAddProduct();
        if(add != null){
            text += ", " + context.getString(R.string.add) + ":" + add.getName();
        }
        text += "}";
        return text + "\n";
    }

}
