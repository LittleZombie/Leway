package com.leway.taipei_hejiang;

import android.app.Application;

import com.leway.taipei_hejiang.utils.SaveUtils;

/**
 * Created by Li Chia-en on 2017/6/27.
 */

public class LewayApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new SaveUtils(this);
    }

}
