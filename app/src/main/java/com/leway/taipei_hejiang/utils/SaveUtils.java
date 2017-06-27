package com.leway.taipei_hejiang.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Li Chia-en on 2017/6/27.
 */

public class SaveUtils {

    private static final String FILE_NAME = "LEWAY_DATA";

    private static final String DATA_COMPANY = "DATA_COMPANY";
    private static final String DATA_NAME = "DATA_NAME";
    private static final String DATA_PHONE = "DATA_PHONE";
    private static final String DATA_FUNCTION = "DATA_FUNCTION";
    private static final String DATA_ADDRESS = "DATA_ADDRESS";

    private static SharedPreferences sharedPreferences;

    public SaveUtils(Context context) {
        if(sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        }
    }

    // Company
    public static void saveCompanyName(String name) {
        sharedPreferences.edit().putString(DATA_COMPANY, name).apply();
    }

    public static String getCompanyName(){
        return sharedPreferences.getString(DATA_COMPANY, "");
    }


    // Name
    public static void saveName(String name) {
        sharedPreferences.edit().putString(DATA_NAME, name).apply();
    }

    public static String getName(){
        return sharedPreferences.getString(DATA_NAME, "");
    }


    // Phone
    public static void savePhone(String name) {
        sharedPreferences.edit().putString(DATA_PHONE, name).apply();
    }

    public static String getPhone(){
        return sharedPreferences.getString(DATA_PHONE, "");
    }


    // Function
    public static void saveFunction(boolean isSelfTake) {
        sharedPreferences.edit().putBoolean(DATA_FUNCTION, isSelfTake).apply();
    }

    public static boolean isSelfTake(){
        return sharedPreferences.getBoolean(DATA_FUNCTION, false);
    }


    // Address
    public static void saveAddress(String address) {
        sharedPreferences.edit().putString(DATA_ADDRESS, address).apply();
    }

    public static String getAddress(){
        return sharedPreferences.getString(DATA_ADDRESS, "");
    }
}
