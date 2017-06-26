package com.leway.taipei_hejiang;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.leway.taipei_hejiang.model.Drink;
import com.leway.taipei_hejiang.model.Product;
import com.leway.taipei_hejiang.utils.CalculateUtil;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Li Chia-en on 2017/5/15.
 */

public class CartAdapter extends BaseAdapter {

    private List<Product> productList;
    private Context context;
    private Gson gson = new Gson();
    private OnClearProductListener listener;

    public CartAdapter(List<Product> productList, Context context, OnClearProductListener listener) {
        this.productList = productList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return productList == null ? 0 : productList.size();
    }

    @Override
    public Product getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.cart_adapter_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        final Product product = getItem(i);
        boolean isDrink = product.isDrink();
        if (isDrink) {
            layoutDrink(viewHolder, product);
        } else {
            layoutFood(viewHolder, product);
        }

        // Image
        int imageResId = context.getResources().getIdentifier(product.getImageName(), "drawable", context.getPackageName());
        viewHolder.imageView.setImageResource(imageResId);

        viewHolder.clearProductImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClearProductClick(product);
            }
        });

        return view;
    }

    private void layoutDrink(ViewHolder viewHolder, Product product) {
        String string = gson.toJson(product);
        Log.e("", "layoutDrink string = " + string);
        Type type = new TypeToken<Drink>() {
        }.getType();
        final Drink drink = new Gson().fromJson(string, type);

        // Name
        String name = drink.getName() + " ("
                + getString(R.string.price) + String.valueOf(drink.getPrice()) + ")";
        viewHolder.nameTextView.setText(name);

        // Temperature
        String hotOrCold = drink.getHotOrCold();
        if (TextUtils.isEmpty(hotOrCold)) {
            viewHolder.temperatureTextView.setVisibility(View.GONE);
        } else {
            viewHolder.temperatureTextView.setVisibility(View.VISIBLE);
            boolean isHot = getString(R.string.hot).contains(hotOrCold)
                    || getString(R.string.warn).contains(hotOrCold);
            String temperature = getString(R.string.temperature) + ":" + hotOrCold;
            viewHolder.temperatureTextView.setText(temperature);
            viewHolder.temperatureTextView.setTextColor(ContextCompat.getColor(context, isHot ? R.color.hot : R.color.cold));
        }

        // Sweet
        if (TextUtils.isEmpty(hotOrCold)) {
            viewHolder.sweetTextView.setVisibility(View.GONE);
        } else {
            viewHolder.sweetTextView.setVisibility(View.VISIBLE);
            if (drink.isSweetFixed()) {
                viewHolder.sweetTextView.setText(getString(R.string.sweet_fixed));
            } else {
                String sweet = getString(R.string.sweet) + ":" + drink.getSweet();
                viewHolder.sweetTextView.setText(sweet);
            }
        }

        // Add
        Product addProduct = drink.getAddProduct();
        if (addProduct != null) {
            String add = getString(R.string.add) + ":" + addProduct.getName();
            String addPrice = "(+" + getString(R.string.price) + String.valueOf(addProduct.getPrice()) + ")";
            String addText = add + addPrice;
            viewHolder.addTextView.setVisibility(View.VISIBLE);
            viewHolder.addTextView.setText(addText);
        } else {
            viewHolder.addTextView.setVisibility(View.GONE);
        }

        // Cup Size
        if (TextUtils.isEmpty(hotOrCold)) {
            viewHolder.sizeTextView.setVisibility(View.GONE);
        } else {
            viewHolder.sizeTextView.setVisibility(View.VISIBLE);
            viewHolder.sizeTextView.setText(drink.getCupSize());
        }

        String cup = String.valueOf(drink.getCount()) + getString(R.string.cup);
        viewHolder.countTextView.setText(cup);

        String price = getString(R.string.price) + String.valueOf(CalculateUtil.oneProduct(drink));
        viewHolder.priceTextView.setText(price);
    }

    private void layoutFood(ViewHolder viewHolder, final Product product) {
        viewHolder.nameTextView.setText(product.getName());

        viewHolder.sweetTextView.setVisibility(View.GONE);
        viewHolder.temperatureTextView.setVisibility(View.GONE);
        viewHolder.addTextView.setVisibility(View.GONE);
        viewHolder.sizeTextView.setVisibility(View.GONE);

        String foodCount = String.valueOf(product.getCount()) + getString(R.string.food_size);
        viewHolder.countTextView.setText(foodCount);

        String price = getString(R.string.price) + String.valueOf(CalculateUtil.oneProduct(product));
        viewHolder.priceTextView.setText(price);
    }

    public interface OnClearProductListener {
        void onClearProductClick(Product product);
    }

    private String getString(int resId) {
        return context.getString(resId);
    }

    public class ViewHolder {
        ImageView imageView;
        TextView nameTextView;
        TextView sweetTextView;
        TextView temperatureTextView;
        TextView addTextView;
        TextView countTextView;
        TextView sizeTextView;
        TextView priceTextView;
        AppCompatImageView clearProductImageView;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.cartAdapterItem_imageView);
            nameTextView = (TextView) view.findViewById(R.id.cartAdapterItem_nameTextView);
            sweetTextView = (TextView) view.findViewById(R.id.cartAdapterItem_sweetTextView);
            temperatureTextView = (TextView) view.findViewById(R.id.cartAdapterItem_temperatureTextView);
            addTextView = (TextView) view.findViewById(R.id.cartAdapterItem_addTextView);
            countTextView = (TextView) view.findViewById(R.id.cartAdapterItem_productCountTextView);
            sizeTextView = (TextView) view.findViewById(R.id.cartAdapterItem_sizeTextView);
            clearProductImageView = (AppCompatImageView) view.findViewById(R.id.cartAdapterItem_clearProductImageView);
            priceTextView = (TextView) view.findViewById(R.id.cartAdapterItem_priceTextView);
        }
    }
}
