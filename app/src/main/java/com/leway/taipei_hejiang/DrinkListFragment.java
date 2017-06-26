package com.leway.taipei_hejiang;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.leway.taipei_hejiang.factory.MenuFactory;
import com.leway.taipei_hejiang.model.Drink;
import com.leway.taipei_hejiang.model.Product;
import com.leway.taipei_hejiang.utils.SaveManager;
import com.leway.taipei_hejiang.utils.ShowUtils;

import java.util.List;

/**
 * Created by Li Chia-en on 2017/5/9.
 */

public class DrinkListFragment extends Fragment implements DrinkListAdapter.OnItemAddToCartClickListener {

    public static final String BUNDLE_KEY = "BUNDLE_KEY";
    private ProductEnum productEnum;

    public static DrinkListFragment getFragmentPage(ProductEnum productEnum) {
        DrinkListFragment fragment = new DrinkListFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_KEY, productEnum);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        productEnum = (ProductEnum) bundle.getSerializable(BUNDLE_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.drink_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (productEnum == null || getView() == null) {
            return;
        }
        layoutListView(getView());
    }

    private void layoutListView(View view) {
        List<Drink> drinks = getDrinks(productEnum);
        DrinkListAdapter adapter = new DrinkListAdapter(drinks, getContext(), this, productEnum);
        ListView listView = (ListView) view.findViewById(R.id.drinkListFragment_listView);
        listView.setAdapter(adapter);
    }

    private List<Drink> getDrinks(ProductEnum productEnum) {
        switch (productEnum) {
            case ORIGINAL:
            default:
                return MenuFactory.generateOriginalDrinks(getContext());
            case SPECIAL:
                return MenuFactory.generateSpecialDrinks(getContext());
            case FRUIT:
                return MenuFactory.generateFruitDrinks(getContext());
            case FAKE_MILK:
                return MenuFactory.generateFakeMilkDrinks(getContext());
            case TRUE_MILK:
                return MenuFactory.generateTrueMilkDrinks(getContext());
            case MILK:
                return MenuFactory.generateMilkDrinks(getContext());
            case MOUNTAIN_TEA:
                return MenuFactory.generateMountainDrinks(getContext());
            case WINNER:
                return MenuFactory.generateWinterDrinks(getContext());
            case SWEET:
                return MenuFactory.generateSweetFoods(getContext());
            case PASTURE:
                return MenuFactory.generatePastures(getContext());
        }
    }


    @Override
    public void onAddToCartClickListener(Drink drink) {
        if (!drink.isDrink()) {
            showFoodDialog(drink);
            return;
        }

        Intent intent = new Intent(getActivity(), OrderDrinkActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(OrderDrinkActivity.BUNDLE_PRODUCT, drink);
        bundle.putSerializable(OrderDrinkActivity.BUNDLE_DRINK_TYPE, productEnum);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void showFoodDialog(final Product product) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.food_dialog, null);
        final EditText cupEditText = (EditText) view.findViewById(R.id.foodDialog_cupEditText);
        cupEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable) || getView() == null) {
                    return;
                }
                cupEditText.removeTextChangedListener(this);

                int cup = Integer.valueOf(editable.toString());
                if (cup <= 0) {
                    cupEditText.setText(String.valueOf(1));
                } else if (cup > 100) {
                    cupEditText.setText(String.valueOf(100));
                }

                cupEditText.addTextChangedListener(this);
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        builder.setPositiveButton(getString(R.string.add_to_cart)
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int count = Integer.valueOf(cupEditText.getText().toString());
                        product.setCount(count);
                        SaveManager.saveProduct(getContext(), product);
                        ShowUtils.showToast(getContext(), getString(R.string.add_to_cart));
                    }
                });
        builder.setNegativeButton(getString(R.string.cancel)
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        builder.show();
    }

}
