package com.leway.taipei_hejiang;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leway.taipei_hejiang.drink_add.AiYuJelly;
import com.leway.taipei_hejiang.drink_add.AloeVera;
import com.leway.taipei_hejiang.drink_add.ChiaSeeds;
import com.leway.taipei_hejiang.drink_add.GrassJelly;
import com.leway.taipei_hejiang.drink_add.SagoDew;
import com.leway.taipei_hejiang.drink_add.Taro;
import com.leway.taipei_hejiang.drink_add.WhiteTapiocaBall;
import com.leway.taipei_hejiang.model.Drink;
import com.leway.taipei_hejiang.model.Product;
import com.leway.taipei_hejiang.utils.CalculateUtil;
import com.leway.taipei_hejiang.utils.SaveManager;
import com.leway.taipei_hejiang.utils.ShowUtils;
import com.leway.taipei_hejiang.view.ChangeLineLayout;

/**
 * Created by Li Chia-en on 2017/5/13.
 */

public class OrderDrinkActivity extends AppCompatActivity implements TextWatcher {

    public static final String BUNDLE_PRODUCT = "BUNDLE_PRODUCT";
    private Drink drink;

    public static final String BUNDLE_DRINK_TYPE = "BUNDLE_DRINK_TYPE";
    private ProductEnum productEnum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_product_activity);

        getExtras();
        layoutContentView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getExtras() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }

        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            return;
        }

        drink = (Drink) bundle.getSerializable(BUNDLE_PRODUCT);
        productEnum = (ProductEnum) bundle.getSerializable(BUNDLE_DRINK_TYPE);
    }

    private void layoutContentView() {
        if (drink == null) {
            return;
        }

        layoutTopLayout();
        layoutTotalPrice();
        layoutCupOrSize();
        layoutCupEditText();

        if (isHigh()) {
            layoutMountainView();
        } else {
            layoutOtherDrinkView();
        }
    }

    private void layoutMountainView() {
        findViewById(R.id.orderDrinkActivity_sweetLayout).setVisibility(View.GONE);
        findViewById(R.id.orderDrinkActivity_temperatureLayout).setVisibility(View.GONE);
        findViewById(R.id.orderDrinkActivity_addLayout).setVisibility(View.GONE);
    }

    private void layoutOtherDrinkView() {
        layoutSweetLayout();
        layoutTemperatureLayout();
    }

    private void layoutCupOrSize() {
        TextView t = (TextView) findViewById(R.id.orderDrinkActivity_cupTextView);
        t.setText(R.string.cup);
    }

    private void layoutTopLayout() {
        ImageView imageView = (ImageView) findViewById(R.id.orderDrinkActivity_imageView);

        TextView nameTextView = (TextView) findViewById(R.id.orderDrinkActivity_nameTextView);
        nameTextView.setText(drink.getName());

        TextView sizeTextView = (TextView) findViewById(R.id.orderDrinkActivity_sizeTextView);
        TextView highMountainTextView = (TextView) findViewById(R.id.orderDrinkActivity_highMountainTextView);
        if (isHigh()) {
            sizeTextView.setVisibility(View.GONE);
            highMountainTextView.setVisibility(View.VISIBLE);
        } else {
            sizeTextView.setText(drink.getCupSize());
            sizeTextView.setVisibility(View.VISIBLE);
            highMountainTextView.setVisibility(View.GONE);
        }

        TextView priceTextView = (TextView) findViewById(R.id.orderDrinkActivity_priceTextView);
        priceTextView.setText(String.valueOf(drink.getPrice()));

    }

    private void layoutSweetLayout() {
        View sweetView = findViewById(R.id.orderDrinkActivity_sweetLayout);
        sweetView.setVisibility(drink.isSweetFixed() ? View.GONE : View.VISIBLE);
    }

    public void onSweetItemClick(View view) {
        TextView clickTextView = (TextView) view;
        drink.setSweet(clickTextView.getText().toString());
        layoutSweetTextView();
    }

    private void layoutSweetTextView() {
        setupSelectedTextView(R.id.orderDrinkActivity_sweetNormalTextView);
        setupSelectedTextView(R.id.orderDrinkActivity_sweetNormalLessTextView);
        setupSelectedTextView(R.id.orderDrinkActivity_sweetHalfTextView);
        setupSelectedTextView(R.id.orderDrinkActivity_sweetLessTextView);
        setupSelectedTextView(R.id.orderDrinkActivity_sweet2TextView);
        setupSelectedTextView(R.id.orderDrinkActivity_sweet1TextView);
        setupSelectedTextView(R.id.orderDrinkActivity_sweetNoTextView);
    }

    private void setupSelectedTextView(int textViewId) {
        String selectedSweet = drink.getSweet();
        if (TextUtils.isEmpty(selectedSweet)) {
            return;
        }

        TextView textView = (TextView) findViewById(textViewId);
        boolean isSelected = selectedSweet.equals(textView.getText().toString());
        textView.setTextColor(ContextCompat.getColor(this, isSelected ? R.color.colorPrimary : R.color.white));
        textView.setTypeface(null, isSelected ? Typeface.BOLD : Typeface.NORMAL);
    }

    private void layoutTemperatureLayout() {
        // Cold
        ChangeLineLayout changeLineLayout = (ChangeLineLayout) findViewById(R.id.orderDrinkActivity_coldFlowLayout);
        String[] coldArrayString = getResources().getStringArray(R.array.temperature_ice);
        for (String coldString : coldArrayString) {
            changeLineLayout.addView(generateColdTextView(coldString));
        }

        // Hot
        LinearLayout hotLayout = (LinearLayout) findViewById(R.id.orderDrinkActivity_hotLinearLayout);
        if (drink.isOnlyCold()) {
            hotLayout.setVisibility(View.GONE);
            return;
        }
        String[] hotArrayString = getResources().getStringArray(R.array.temperature_hot);
        for (String hotString : hotArrayString) {
            hotLayout.addView(generateHotTextView(hotString));
        }
    }

    private View generateColdTextView(String coldString) {
        View view = LayoutInflater.from(this).inflate(R.layout.temperature_ice_textview, null);
        TextView textView = (TextView) view.findViewById(R.id.temperatureIceTextView);
        textView.setText(coldString);
        textView.setOnClickListener(coldClickListener);
        return view;
    }

    private View generateHotTextView(String hotString) {
        View view = LayoutInflater.from(this).inflate(R.layout.temperature_hot_textview, null);
        TextView textView = (TextView) view.findViewById(R.id.temperatureHotTextView);
        textView.setText(hotString);
        textView.setOnClickListener(hotClickListener);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, getResources().getDimensionPixelSize(R.dimen.dimen_8), 0);

        view.setLayoutParams(params);
        return view;
    }

    private View.OnClickListener coldClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            TextView textView = (TextView) view;
            drink.setHotOrCold(textView.getText().toString());
            setupSelectedColdTextView();
            setupSelectedHotTextView();
        }
    };

    private View.OnClickListener hotClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            TextView textView = (TextView) view;
            drink.setHotOrCold(textView.getText().toString());
            setupSelectedHotTextView();
            setupSelectedColdTextView();
        }
    };

    private void setupSelectedColdTextView() {
        String selectedCold = drink.getHotOrCold();
        if (TextUtils.isEmpty(selectedCold)) {
            return;
        }

        ChangeLineLayout changeLineLayout = (ChangeLineLayout) findViewById(R.id.orderDrinkActivity_coldFlowLayout);
        for (int index = 0; index < changeLineLayout.getChildCount(); index++) {
            View view = changeLineLayout.getChildAt(index);
            TextView textView = (TextView) view.findViewById(R.id.temperatureIceTextView);
            boolean isSelected = selectedCold.equals(textView.getText().toString());

            textView.setBackgroundColor(isSelected ? ContextCompat.getColor(this, R.color.cold) : Color.TRANSPARENT);
            textView.setTextColor(ContextCompat.getColor(this, isSelected ? R.color.white : R.color.colorAccent));
        }
    }

    private void setupSelectedHotTextView() {
        String hot = drink.getHotOrCold();
        if (TextUtils.isEmpty(hot) || drink.isOnlyCold()) {
            return;
        }

        LinearLayout hotLayout = (LinearLayout) findViewById(R.id.orderDrinkActivity_hotLinearLayout);
        for (int index = 0; index < hotLayout.getChildCount(); index++) {
            View view = hotLayout.getChildAt(index);
            TextView textView = (TextView) view.findViewById(R.id.temperatureHotTextView);
            boolean isSelected = hot.equals(textView.getText().toString());

            textView.setBackgroundColor(isSelected ? ContextCompat.getColor(this, R.color.hot) : Color.TRANSPARENT);
            textView.setTextColor(ContextCompat.getColor(this, isSelected ? R.color.white : R.color.colorAccent));
        }
    }

    private void layoutAddLayout() {
        setupSelectedAddItem(R.id.orderDrinkActivity_addGrassJellyTextView);
        setupSelectedAddItem(R.id.orderDrinkActivity_addSagoDewTextView);
        setupSelectedAddItem(R.id.orderDrinkActivity_addAiYuTextView);
        setupSelectedAddItem(R.id.orderDrinkActivity_addWhiteTapiocaBallTextView);
        setupSelectedAddItem(R.id.orderDrinkActivity_addAloeVeraTextView);
        setupSelectedAddItem(R.id.orderDrinkActivity_addTaroTextView);
        setupSelectedAddItem(R.id.orderDrinkActivity_addChiaSeedsTextView);
        layoutTotalPrice();
    }

    @SuppressWarnings("ConstantConditions")
    public void onAddItemClick(View view) {
        Product product = null;
        switch (view.getId()) {
            case R.id.orderDrinkActivity_addGrassJellyTextView:
                product = new GrassJelly(this);
                break;
            case R.id.orderDrinkActivity_addSagoDewTextView:
                product = new SagoDew(this);
                break;
            case R.id.orderDrinkActivity_addAiYuTextView:
                product = new AiYuJelly(this);
                break;
            case R.id.orderDrinkActivity_addWhiteTapiocaBallTextView:
                product = new WhiteTapiocaBall(this);
                break;
            case R.id.orderDrinkActivity_addAloeVeraTextView:
                product = new AloeVera(this);
                break;
            case R.id.orderDrinkActivity_addTaroTextView:
                product = new Taro(this);
                break;
            case R.id.orderDrinkActivity_addChiaSeedsTextView:
                product = new ChiaSeeds(this);
                break;
        }

        Product selectedProduct = drink.getAddProduct();
        if (selectedProduct == null) {
            drink.setAddProduct(product);
        } else {
            if (selectedProduct.getName().equals(product.getName())) {
                drink.setAddProduct(null);
            } else {
                drink.setAddProduct(product);
            }
        }
        layoutAddLayout();
    }

    private void setupSelectedAddItem(int textViewId) {
        TextView textView = (TextView) findViewById(textViewId);

        if (drink.getAddProduct() == null) {
            textView.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
            textView.setBackgroundResource(R.drawable.add);
            return;
        }

        Product add = drink.getAddProduct();
        String selectedAddItem = add.getName();
        boolean isSelected = selectedAddItem.equals(textView.getText().toString());
        textView.setTextColor(ContextCompat.getColor(this, isSelected ? R.color.white : R.color.colorAccent));
        if (isSelected) {
            textView.setBackgroundColor(ContextCompat.getColor(this, R.color.add));
        } else {
            textView.setBackgroundResource(R.drawable.add);
        }
    }

    private void layoutCupEditText() {
        EditText cup = (EditText) findViewById(R.id.orderDrinkActivity_cupEditText);
        cup.addTextChangedListener(this);
    }

    private void layoutTotalPrice() {
        String price = String.valueOf(CalculateUtil.oneProduct(drink));
        ((TextView) findViewById(R.id.orderDrinkActivity_totalPriceTextView)).setText(price);
    }

    private String getCup() {
        return ((EditText) findViewById(R.id.orderDrinkActivity_cupEditText)).getText().toString();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (TextUtils.isEmpty(editable)) {
            return;
        }
        EditText cupEditText = (EditText) findViewById(R.id.orderDrinkActivity_cupEditText);
        cupEditText.removeTextChangedListener(this);

        int cup = Integer.valueOf(editable.toString());
        if (cup <= 0) {
            cupEditText.setText(String.valueOf(1));
        } else if (cup > 100) {
            cupEditText.setText(String.valueOf(100));
        }

        cupEditText.addTextChangedListener(this);
        drink.setCount(Integer.valueOf(getCup()));
        layoutTotalPrice();
    }

    public void onAddToCartClick(View view) {
        if (isHigh()) {
            SaveManager.saveProduct(this, drink);
            ShowUtils.showToast(this, getString(R.string.add_to_cart));
            finish();
            return;
        }

        String warningMessage = generateWarningMessage();
        if (!TextUtils.isEmpty(warningMessage)) {
            ShowUtils.showToast(this, warningMessage);
            return;
        }

        SaveManager.saveProduct(this, drink);
        ShowUtils.showToast(this, getString(R.string.add_to_cart));
        finish();
    }

    private boolean isHigh(){
        return productEnum == ProductEnum.MOUNTAIN_TEA;
    }

    private String generateWarningMessage() {
        String message = "";
        if (!drink.isSweetFixed() && TextUtils.isEmpty(drink.getSweet())) {
            message += getString(R.string.not_selected_sweet);
        }

        if (TextUtils.isEmpty(drink.getHotOrCold())) {
            if (!TextUtils.isEmpty(message)) {
                message += "\n";
            }
            message += getString(R.string.not_selected_temperature);
        }
        return message;
    }
}
