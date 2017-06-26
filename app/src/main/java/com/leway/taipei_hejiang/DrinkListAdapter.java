package com.leway.taipei_hejiang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.leway.taipei_hejiang.model.Drink;

import java.util.List;

/**
 * Created by Li Chia-en on 2017/5/10.
 */

public class DrinkListAdapter extends BaseAdapter {

    private List<Drink> productList;
    private LayoutInflater inflater;
    private OnItemAddToCartClickListener listener;
    private ProductEnum productEnum;

    public DrinkListAdapter(List<Drink> productList, Context context
            , OnItemAddToCartClickListener listener, ProductEnum productEnum) {
        this.productList = productList;
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
        this.productEnum = productEnum;
    }

    @Override
    public int getCount() {
        return productList == null ? 0 : productList.size();
    }

    @Override
    public Drink getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.product_list_adapter, null);

            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view.findViewById(R.id.productListAdapter_imageView);
            viewHolder.nameTextView = (TextView) view.findViewById(R.id.productListAdapter_nameTextView);
            viewHolder.coldTextView = (TextView) view.findViewById(R.id.productListAdapter_coldTextView);
            viewHolder.hotTextView = (TextView) view.findViewById(R.id.productListAdapter_hotTextView);
            viewHolder.sweetTextView = (TextView) view.findViewById(R.id.productListAdapter_sweetTextView);
            viewHolder.sizeTextView = (TextView) view.findViewById(R.id.productListAdapter_sizeTextView);
            viewHolder.priceTextView = (TextView) view.findViewById(R.id.productListAdapter_priceTextView);
            viewHolder.addToCartImageView = (ImageView) view.findViewById(R.id.productListAdapter_addToCartTextView);
            viewHolder.drinkInfoLayout = view.findViewById(R.id.productListAdapter_infoLayout);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        final Drink drink = productList.get(position);
        Context context = view.getContext();

        // Name
        viewHolder.nameTextView.setText(drink.getName());

        // Image
        int imageResId = context.getResources().getIdentifier(drink.getImageName(), "drawable", context.getPackageName());
        viewHolder.imageView.setImageResource(imageResId);

        // Price
        viewHolder.priceTextView.setText(String.valueOf(drink.getPrice()));

        // drink size
        if (!drink.isDrink() || isHigh()) {
            viewHolder.sizeTextView.setVisibility(View.GONE);
        } else {
            viewHolder.sizeTextView.setText(drink.getCupSize());
            viewHolder.sizeTextView.setVisibility(View.VISIBLE);
        }

        // sweet
        viewHolder.sweetTextView.setVisibility(drink.isSweetFixed() ? View.VISIBLE : View.GONE);

        // hot and cold
        boolean isOnlyCold = drink.isOnlyCold();
        viewHolder.coldTextView.setVisibility(View.VISIBLE);
        viewHolder.hotTextView.setVisibility(isOnlyCold ? View.GONE : View.VISIBLE);

        // Click Event
        viewHolder.addToCartImageView.setTag(drink);
        viewHolder.addToCartImageView.setOnClickListener(clickListener);

        viewHolder.drinkInfoLayout.setVisibility(drink.isDrink() && !isHigh() ? View.VISIBLE : View.GONE);

        return view;
    }

    private boolean isHigh(){
        return productEnum == ProductEnum.MOUNTAIN_TEA;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Drink drink = (Drink) view.getTag();
            if (drink == null) {
                return;
            }
            listener.onAddToCartClickListener(drink);
        }
    };

    public class ViewHolder {
        ImageView imageView;
        TextView nameTextView;
        TextView coldTextView;
        TextView hotTextView;
        TextView sweetTextView;
        TextView sizeTextView;
        TextView priceTextView;
        ImageView addToCartImageView;

        View drinkInfoLayout;
    }

    public interface OnItemAddToCartClickListener {
        void onAddToCartClickListener(Drink drink);
    }
}
