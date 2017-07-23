package com.leway.taipei_hejiang.factory;

import android.content.Context;

import com.leway.taipei_hejiang.menu.pasture.PastureIceCream;
import com.leway.taipei_hejiang.menu.pasture.PastureMilk;
import com.leway.taipei_hejiang.menu.sweet_food.SweetFoodCheeseCake;
import com.leway.taipei_hejiang.menu.sweet_food.SweetFoodIceCreamPuffs;
import com.leway.taipei_hejiang.menu.sweet_food.SweetFoodLemonCake;
import com.leway.taipei_hejiang.menu.tea_fruit.FruitKumquatChiaTea;
import com.leway.taipei_hejiang.menu.tea_fruit.FruitLemonAiYuChiaSeedsTea;
import com.leway.taipei_hejiang.menu.tea_milk.MilkGrassJelly;
import com.leway.taipei_hejiang.menu.tea_milk.MilkMelon;
import com.leway.taipei_hejiang.menu.tea_milk.MilkOvaltine;
import com.leway.taipei_hejiang.menu.tea_milk.MilkTaro;
import com.leway.taipei_hejiang.menu.tea_milk.MilkTaroIce;
import com.leway.taipei_hejiang.menu.tea_milk.MilkTaroSagoDew;
import com.leway.taipei_hejiang.menu.tea_milk.MilkTaroTapioca;
import com.leway.taipei_hejiang.menu.tea_mountain.MountainTeaAiLi;
import com.leway.taipei_hejiang.menu.tea_mountain.MountainTeaHigh;
import com.leway.taipei_hejiang.menu.tea_true_milk.TrueMilkBlackLatte;
import com.leway.taipei_hejiang.menu.tea_true_milk.TrueMilkFreshMilkTea;
import com.leway.taipei_hejiang.menu.tea_true_milk.TrueMilkGrassJellyBlackLatte;
import com.leway.taipei_hejiang.menu.tea_true_milk.TrueMilkGrassLatte;
import com.leway.taipei_hejiang.menu.tea_true_milk.TrueMilkGreenLatte;
import com.leway.taipei_hejiang.menu.tea_true_milk.TrueMilkLatte;
import com.leway.taipei_hejiang.menu.tea_true_milk.TrueMilkMyLatte;
import com.leway.taipei_hejiang.menu.tea_true_milk.TrueMilkOolongLatte;
import com.leway.taipei_hejiang.menu.tea_true_milk.TrueMilkTapiocaBlackLatte;
import com.leway.taipei_hejiang.menu.tea_winter.WinterBigOceanTea;
import com.leway.taipei_hejiang.menu.tea_winter.WinterBlackSugarTea;
import com.leway.taipei_hejiang.menu.tea_winter.WinterGingerMilkTea;
import com.leway.taipei_hejiang.menu.tea_winter.WinterLonganTea;
import com.leway.taipei_hejiang.model.Drink;
import com.leway.taipei_hejiang.menu.tea_fake_milk.FakeMilkBlackMilkTea;
import com.leway.taipei_hejiang.menu.tea_fake_milk.FakeMilkGrassMilkTea;
import com.leway.taipei_hejiang.menu.tea_fake_milk.FakeMilkGreenMilkTea;
import com.leway.taipei_hejiang.menu.tea_fake_milk.FakeMilkOolongMilkTea;
import com.leway.taipei_hejiang.menu.tea_fake_milk.FakeMilkSmellOvaltine;
import com.leway.taipei_hejiang.menu.tea_fake_milk.FakeMilkTaroMilkTea;
import com.leway.taipei_hejiang.menu.tea_fake_milk.FakeMilkWhiteTapiocaMilkTea;
import com.leway.taipei_hejiang.menu.tea_fruit.FruitEmeraldLemonGreen;
import com.leway.taipei_hejiang.menu.tea_fruit.FruitGrapeFruitGreen;
import com.leway.taipei_hejiang.menu.tea_fruit.FruitKumquatLemonTea;
import com.leway.taipei_hejiang.menu.tea_fruit.FruitKumquatTea;
import com.leway.taipei_hejiang.menu.tea_fruit.FruitLemonAiYuJelly;
import com.leway.taipei_hejiang.menu.tea_fruit.FruitLemonHoney;
import com.leway.taipei_hejiang.menu.tea_fruit.FruitOrangeGreenTea;
import com.leway.taipei_hejiang.menu.tea_fruit.FruitQQTea;
import com.leway.taipei_hejiang.menu.tea_fruit.FruitSmellDoDo;
import com.leway.taipei_hejiang.menu.tea_fruit.FruitSmellFreshGreenTea;
import com.leway.taipei_hejiang.menu.tea_fruit.FruitSmellLemonTea;
import com.leway.taipei_hejiang.menu.tea_original.OriginalFishBlackTea;
import com.leway.taipei_hejiang.menu.tea_original.OriginalFourSeasonTea;
import com.leway.taipei_hejiang.menu.tea_original.OriginalGrassTea;
import com.leway.taipei_hejiang.menu.tea_original.OriginalGreenTea;
import com.leway.taipei_hejiang.menu.tea_original.OriginalMyTea;
import com.leway.taipei_hejiang.menu.tea_original.OriginalOolongTea;
import com.leway.taipei_hejiang.menu.tea_original.OriginalSouthAfricaTea;
import com.leway.taipei_hejiang.menu.tea_original.OriginalSweetTea;
import com.leway.taipei_hejiang.menu.tea_special.SpecialDoDoGreenTea;
import com.leway.taipei_hejiang.menu.tea_special.SpecialGrassTea;
import com.leway.taipei_hejiang.menu.tea_special.SpecialLemonBlackTea;
import com.leway.taipei_hejiang.menu.tea_special.SpecialLemonDoDoTea;
import com.leway.taipei_hejiang.menu.tea_special.SpecialLemonTea;
import com.leway.taipei_hejiang.menu.tea_special.SpecialMelonGreenTea;
import com.leway.taipei_hejiang.menu.tea_special.SpecialMelonLemon;
import com.leway.taipei_hejiang.menu.tea_special.SpecialMelonMyTea;
import com.leway.taipei_hejiang.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Li Chia-en on 2017/5/11.
 */

public class MenuFactory {

    public static List<Drink> generateOriginalDrinks(Context context) {
        List<Drink> drinks = new ArrayList<>();
        drinks.add(new OriginalMyTea(context));
        drinks.add(new OriginalFishBlackTea(context));
        drinks.add(new OriginalGreenTea(context));
        drinks.add(new OriginalFourSeasonTea(context));
        drinks.add(new OriginalOolongTea(context));
        drinks.add(new OriginalGrassTea(context));
        drinks.add(new OriginalSweetTea(context));
        drinks.add(new OriginalSouthAfricaTea(context));
        return drinks;
    }

    public static List<Drink> generateSpecialDrinks(Context context) {
        List<Drink> drinks = new ArrayList<>();
        drinks.add(new SpecialMelonGreenTea(context));
        drinks.add(new SpecialMelonMyTea(context));
        drinks.add(new SpecialMelonLemon(context));
        drinks.add(new SpecialLemonBlackTea(context));
        drinks.add(new SpecialLemonTea(context));
        drinks.add(new SpecialDoDoGreenTea(context));
        drinks.add(new SpecialGrassTea(context));
        drinks.add(new SpecialLemonDoDoTea(context));
        return drinks;
    }

    public static List<Drink> generateFruitDrinks(Context context) {
        List<Drink> drinks = new ArrayList<>();
        drinks.add(new FruitSmellFreshGreenTea(context));
        drinks.add(new FruitOrangeGreenTea(context));
        drinks.add(new FruitEmeraldLemonGreen(context));
        drinks.add(new FruitSmellLemonTea(context));
        drinks.add(new FruitLemonAiYuJelly(context));
        drinks.add(new FruitKumquatTea(context));
        drinks.add(new FruitKumquatLemonTea(context));
        drinks.add(new FruitLemonHoney(context));
        drinks.add(new FruitSmellDoDo(context));
        drinks.add(new FruitQQTea(context));
        drinks.add(new FruitGrapeFruitGreen(context));
        drinks.add(new FruitLemonAiYuChiaSeedsTea(context));
        drinks.add(new FruitKumquatChiaTea(context));
        return drinks;
    }

    public static List<Drink> generateFakeMilkDrinks(Context context) {
        List<Drink> drinks = new ArrayList<>();
        drinks.add(new FakeMilkOolongMilkTea(context));
        drinks.add(new FakeMilkBlackMilkTea(context));
        drinks.add(new FakeMilkGreenMilkTea(context));
        drinks.add(new FakeMilkGrassMilkTea(context));
        drinks.add(new FakeMilkSmellOvaltine(context));
        drinks.add(new FakeMilkWhiteTapiocaMilkTea(context));
        drinks.add(new FakeMilkTaroMilkTea(context));
        return drinks;
    }

    public static List<Drink> generateTrueMilkDrinks(Context context) {
        List<Drink> drinks = new ArrayList<>();
        drinks.add(new TrueMilkBlackLatte(context));
        drinks.add(new TrueMilkOolongLatte(context));
        drinks.add(new TrueMilkGreenLatte(context));
        drinks.add(new TrueMilkGrassLatte(context));
        drinks.add(new TrueMilkMyLatte(context));
        drinks.add(new TrueMilkGrassJellyBlackLatte(context));
        drinks.add(new TrueMilkLatte(context));
        drinks.add(new TrueMilkTapiocaBlackLatte(context));
        drinks.add(new TrueMilkFreshMilkTea(context));
        return drinks;
    }

    public static List<Drink> generateMilkDrinks(Context context) {
        List<Drink> drinks = new ArrayList<>();
        drinks.add(new MilkOvaltine(context));
        drinks.add(new MilkMelon(context));
        drinks.add(new MilkGrassJelly(context));
        drinks.add(new MilkTaroIce(context));
        drinks.add(new MilkTaroSagoDew(context));
        drinks.add(new MilkTaro(context));
        drinks.add(new MilkTaroTapioca(context));
        return drinks;
    }

    public static List<Drink> generateMountainDrinks(Context context) {
        List<Drink> drinks = new ArrayList<>();
        drinks.add(new MountainTeaAiLi(context));
        drinks.add(new MountainTeaHigh(context));
        return drinks;
    }

    public static List<Drink> generateWinterDrinks(Context context) {
        List<Drink> drinks = new ArrayList<>();
        drinks.add(new WinterBigOceanTea(context));
        drinks.add(new WinterBlackSugarTea(context));
        drinks.add(new WinterGingerMilkTea(context));
        drinks.add(new WinterLonganTea(context));
        return drinks;
    }

    public static List<Drink> generateSweetFoods(Context context) {
        List<Drink> sweetFoods = new ArrayList<>();
        sweetFoods.add(new SweetFoodCheeseCake(context));
        sweetFoods.add(new SweetFoodIceCreamPuffs(context));
        sweetFoods.add(new SweetFoodLemonCake(context));
        return sweetFoods;
    }

    public static List<Drink> generatePastures(Context context) {
        List<Drink> sweetFoods = new ArrayList<>();
        sweetFoods.add(new PastureMilk(context));
        sweetFoods.add(new PastureIceCream(context));
        return sweetFoods;
    }

}
