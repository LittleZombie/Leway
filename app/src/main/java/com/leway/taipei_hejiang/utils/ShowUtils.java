package com.leway.taipei_hejiang.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Li Chia-en on 2017/5/15.
 */

public class ShowUtils {

    public static void showToast(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

}
